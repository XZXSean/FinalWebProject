/**
 * Created by 是不是傻 on 2017/6/14.
 * 6.14 19.56 重构项目
 *
 * 客户端给服务端发送心跳，如果表被删除了 就
 * 前后端共同维持2个表，前端的表包括所有已经展示过的信息，后端表也是如此
 * 删除 命令发送给服务端，服务端从数据库中删除对应的item，删除成功则删除后端表，返回删除成功消息给前端，前端删除表中对应item
 * 修改 命令发送给服务端，服务端从数据库中修改对应的item,修改成功则修改后端表，返回修改成功消息给前端，前段删除表中对应Item
 * 查看 命令发送给服务端，服务端从数据库中获得对应的item,返回所有数据，前端展示
 * 增加 命令发送给服务端，服务端向数据库中增加对应的item,返回执行结果，前端增加对应行
 * 后端有一个数据库的映射表，别人修改了数据库 并不会导致你的映射表发生变化
 */

//初始化table并和toolbar绑定
createBootstrapTable('#table', '#toolbar');

$("#btn_add").bind("click", function (e) {
    $("#addModal").modal();
})

$("#btn_edit").bind("click", function (e) {
    var selectItems = $("#table").bootstrapTable('getSelections');
    if (selectItems.length == 0)
        return;
    $("#editId").val(selectItems[0].id);
    $("#editName").val(selectItems[0].name);
    $("#editDepartment").val(selectItems[0].department);
    $("#editDescription").val(selectItems[0].description);
    $.ajax({
        type: 'get',
        url: "http://localhost:8080/web/getDetailById?id=" + selectItems[0].id,
        success: function (data) {
            var temp = eval('('+data+')');
            $("#editAddress").val(temp.address);
            $("#editDate").val(temp.date);
            $("#editComments").val(temp.comments);
            $("#modifyModal").modal();
        },
        error: function () {
            alert("添加细节出错");
        }
    })
})

$("#btn_delete").bind("click", function (e) {
    var a = $("#table").bootstrapTable('getSelections');
    if (selectItems.length == 0)
        return;
    alert(a[0].description);
})

$("#btn_display").bind("click", function (e) {
    var selectItems = $("#table").bootstrapTable('getSelections');
    if (selectItems.length == 0)
        return;
    $("#detailId").val(selectItems[0].id);
    $("#detailName").val(selectItems[0].name);
    $("#detailDepartment").val(selectItems[0].department);
    $("#detailDescription").val(selectItems[0].description);
    $.ajax({
        type: 'get',
        url: "http://localhost:8080/web/getDetailById?id=" + selectItems[0].id,
        success: function (data) {
            var temp = eval('('+data+")");
            $("#detailAddress").val(temp.address);
            $("#detailDate").val(temp.date);
            $("#detailComments").val(temp.comments);
            $("#detailModal").modal();
        },
        error: function () {
            alert("添加细节出错");
        }
    })

})

$("#btn_search").bind("click", function (e) {
    addData($("#searchContent").val());
})

$("#btn_clear").bind("click", function (e) {
    $("#searchContent").val("");
})

$("#addClear").bind("click", function (e) {
    $("#addId").val("");
    $("#addName").val("");
    $("#addDepartment").val("");
    $("#addDescription").val("");
    $("#addAddress").val("");
    $("#addComments").val("");
    $("#addDate").val("");
})
$("#add").bind("click", function (e) {
    var data = {
        id: $("#addId").val(),
        name: $("#addName").val(),
        department: $("#addDepartment").val(),
        description: $("#addDescription").val(),
        address: $("#addAddress").val(),
        comments: $("#addComments").val(),
        date: $("#addDate").val()
    };
    $.ajax({
        type: 'post',
        data: data,
        url: "http://localhost:8080/web/getlist?searchContent=" + searchContent,
        success: function (data) {
            $("#table").bootstrapTable("load", eval(data));
        },
        error: function () {
            alert("err");
        }
    })
})
$("#editClear").bind("click", function (e) {
    $("#editId").val("");
    $("#editName").val("");
    $("#editDepartment").val("");
    $("#editDescription").val("");
    $("#editAddress").val("");
    $("#editComments").val("");
    $("#editDate").val("");
})
$("#edit").bind("click", function (e) {
    var data = {
        id: $("#editId").val(),
        name: $("#editName").val(),
        department: $("#editDepartment").val(),
        description: $("#editDescription").val(),
        address: $("#editAddress").val(),
        comments: $("#editComments").val(),
        date: $("#editDate").val()
    };
    $.ajax({
        type: 'post',
        data: data,
        url: "http://localhost:8080/web/getlist?searchContent=" + searchContent,
        success: function (data) {
            $("#table").bootstrapTable("load", eval(data));
        },
        error: function () {
            alert("err");
        }
    })
})


$(".search input").attr('placeholder', "列表内搜索")


function createBootstrapTable(table, toolbar) {
    init(table, toolbar);
}

function init(table, toolbar) {
    $(table).bootstrapTable({
        toolbar: toolbar,                   //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                    //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 8,                       //每页的记录行数（*）
        pageList: [8, 10, 20, 50],            //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,                 //严格搜索
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 500,                         //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列

        showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        columns: [
            {
                width: "5%",
                checkbox: true,
            },
            {
                field: "id",
                title: "现状环境整治编号",
                width: "20%",
                sortable: true,
            },
            {
                field: "name",
                title: "名字",
                width: "20%",
            },
            {
                field: "department",
                title: "所属文物保护单位",
                width: "20%",
            },
            {
                field: "description",
                title: "整治说明",
                width: "35%",
            }
        ],
        //传递参数（*），这里应该返回一个object，即形如{param1:val1,param2:val2}
        /* sidePagination:server模式下的时候 需要添加的选项
         url: url,                           //请求后台的URL（*）
         method: 'post',                     //请求方式（*）
         dataType: "json",                   //json格式数据
         queryParams: function queryParams(params) {
         var param = {
         pageNumber: params.pageNumber,
         pageSize: params.pageSize,
         orderNum: $("#orderNum").val()
         };
         return param;
         },
         onLoadSuccess: function () {  //加载成功时执行
         layer.msg("加载成功");
         },
         onLoadError: function () {  //加载失败时执行
         layer.msg("加载数据失败", {time: 1500, icon: 2});
         }

         */
    });
}

function addData(searchContent) {
    $.ajax({
        type: 'get',
        url: "http://localhost:8080/web/getlist?searchContent=" + searchContent,
        success: function (data) {
            $("#table").bootstrapTable("load", eval(data));
        },
        error: function () {
            //请求出错处理
            alert("err");
        }
    })
}
