<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Книжная полка</title>
    <link rel="stylesheet" type="text/css" href="resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="resources/themes/icon.css">
    <script type="text/javascript" src="resources/jquery.min.js"></script>
    <script type="text/javascript" src="resources/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="resources/plugins/jquery.edatagrid.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<label for="report_type">Отчёт с группировкой по:</label>
<select id="report_type" onclick="$('#btn').attr('href','report?groupby='+$('#report_type').val())">
    <option value="author">Авторам</option>
    <option value="year">Годам</option>
</select>
<a id="btn" class="easyui-linkbutton" target="_blank">Сформировать</a>

<table id="booksgrid" title="Книги" class="easyui-datagrid"
       style="height:400px"
       toolbar="#toolbar" idField="id"
       method="get"
       url="./api/books/"
       rownumbers="true" fitColumns="true" singleSelect="true" pagination="true">
    <thead>
    <tr>
        <th field="title" width="50" editor="{type:'validatebox'}">Наименование</th>
        <th field="author" width="50" editor="{type:'validatebox'}">Автор</th>
        <th field="year" width="10" editor="{type:'validatebox'}">Год издания</th>
    </tr>

    </thead>
</table>
<div id="toolbar">
    <div>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newBook()">Добавить</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editBook()">Изменить</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
           onclick="destroyBook()">Удалить</a>
    </div>
    <div style="padding:3px">
        Наименование:
        <input id="title" style="easyui-">
        Автор:
        <input id="author" style="line-height:26px;border:1px solid #ccc">
        Год издания:
        <input id="year" style="line-height:26px;border:1px solid #ccc">
        <a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">Искать</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
        <div style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">О книге</div>
        <div style="margin-bottom:10px">
            <input name="title" class="easyui-textbox" required="true" label="Название"
                   style="line-height:26px;border:1px solid #ccc">
        </div>
        <div style="margin-bottom:10px">
            <input name="author" class="easyui-textbox" required="true" label="Автор"
                   style="line-height:26px;border:1px solid #ccc">
        </div>
        <div style="margin-bottom:10px">
            <input name="year" class="easyui-textbox" required="true" label="Год издания"
                   style="line-height:26px;border:1px solid #ccc">
            <input name="id" id="id" type="hidden" required="false">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveBook()" style="width:90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
</div>
<script type="text/javascript">
    $('#btn').attr('href', 'report?groupby=' + $('#report_type').val());
    var url;

    function newBook() {
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', 'Добавить');
        $('#fm').form('clear');
        url = 'saveBook';
    }

    function editBook() {
        var row = $('#booksgrid').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', 'Изменить');
            $('#fm').form('load', row);
            url = 'saveBook';
        }
    }

    function saveBook() {
        $('#fm').form('submit', {
            url: url,
            onSubmit: function () {
                if (!$('#id').val()) {
                    $('#id').val('0');
                }
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.errorMsg) {
                    $.messager.show({
                        title: 'Error',
                        msg: result.errorMsg
                    });
                } else {
                    $('#dlg').dialog('close');
                    $('#booksgrid').datagrid('reload');
                }
            }
        });
    }

    function destroyBook() {
        var row = $('#booksgrid').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Подтвердить удаление', 'Вы уверены, что хотите удалить книгу?', function (r) {
                if (r) {
                    $.post('deleteBook', {id: row.id}, function (result) {
                        if (result.success) {
                            $('#booksgrid').datagrid('reload');	// reload the user data
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: result.errorMsg
                            });
                        }
                    }, 'json');
                }
            });
        }
    }

    function doSearch() {
        if ($('#title').val() != '' || $('#author').val() != '' || $('#year').val() != '') {
            $('#booksgrid').datagrid('load', {
                title: $('#title').val(),
                author: $('#author').val(),
                year: $('#year').val()
            });
            $('#booksgrid').datagrid('reload');
        }
        else {
            $('#booksgrid').datagrid('load', {});
        }
    }
</script>


</body>
</html>
