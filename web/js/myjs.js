
$(function () {
    $("[data-toggle='popover']").popover();
});

//初始化table并和toolbar绑定
createBootstrapTable('#table', '#toolbar');

$("#btn_search").bind("click", function () {
    addData($("#searchContent").val());
});

$("#btn_clear").bind("click", function () {
    $("#searchContent").val("");
});

$("#btn_add").bind("click", function () {
    $("#addModal").modal();
});

$("#btn_edit").bind("click", function () {
    var selectItems = $("#table").bootstrapTable('getSelections');
    if (selectItems.length == 0)
        return;
    //修改的话 直接展示所有信息就好了
    // var a=($("#table").bootstrapTable('getRowByUniqueId',selectItems[0].id)).number;
    //    alert(a);
    // clickRowIndex=selectItems[0].number;
    $("#editId").val(selectItems[0].id);
    $("#editName").val(selectItems[0].name);
    $("#editDepartment").val(selectItems[0].department);
    $("#editDescription").val(selectItems[0].description);
    $("#editAddress").val(selectItems[0].address);
    $("#editDate").val(selectItems[0].date);
    $("#editComments").val(selectItems[0].comments);
    $("#modifyModal").modal();
});

$("#btn_delete").bind("click", function () {
    var selectItems = $("#table").bootstrapTable('getSelections');
    if (selectItems.length == 0)
        return;
    //将delete的所选行的所有Id全部发送给服务器
    var data1 = new Array();
    for (var i = 0; i < selectItems.length; i++)
        data1[i] = selectItems[i].id;
    $.ajax({
        type: 'post',
        data: {
            ids: data1
        },
        traditional: true,
        url: "http://localhost:8080/web/deleteItems",
        success: function (data) {
            if (data == "删除成功") {
                for (var i = 0; i < data1.length; i++)
                    $("#table").bootstrapTable('removeByUniqueId', data1[i]);
                alert("删除成功");
            } else
                alert("删除失败");
        },
        error: function () {
            alert("err");
        }
    })
});

$("#btn_display").bind("click", function () {
    var selectItems = $("#table").bootstrapTable('getSelections');
    if (selectItems.length == 0)
        return;
    $("#detailId").text(selectItems[0].id);
    $("#detailName").text(selectItems[0].name);
    $("#detailDepartment").text(selectItems[0].department);
    $("#detailDescription").text(selectItems[0].description);
    $.ajax({
        type: 'get',
        url: "http://localhost:8080/web/displayItem?id=" + selectItems[0].id,
        success: function (data) {
            var temp = eval('(' + data + ")");
            $("#detailAddress").text(temp.address);
            $("#detailDate").text(temp.date);
            $("#detailComments").text(temp.comments);
            $("#detailModal").modal();
        },
        error: function () {
            alert("添加细节出错");
        }
    })

})

$("#addClear").bind("click", function () {
    addModalClear();
});
$("#add").bind("click", function () {
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
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8080/web/addItem",
        success: function (data) {
            if (data == "添加成功") {
                refreshData();
                addModalClear();
                alert("添加成功");
                $("#addModal").modal("hide");
            } else
                alert("添加失败");
        },
        error: function () {
            alert("err");
        }
    })
});
$("#editClear").bind("click", function () {
    modifyModalClear();
});
$("#edit").bind("click", function () {
    var data1 = {
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
        url: "http://localhost:8080/web/modifyItem",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: data1,
        success: function (data) {
            if (data == "修改成功") {
                refreshData();
                modifyModalClear();
                $("#modifyModal").modal("hide");
                alert("修改成功");
            } else
                alert("修改失败");
        },
        error: function () {
            alert("修改出错");
        }
    })
});

$(".search input").attr('placeholder', "列表内搜索");

//查看按钮点击的弹出层 中的list 点击切换active
$(".list-group-item").bind("click",function () {
    $(".list-group-item").removeClass("active");
    $(this).addClass("active");
});

//点击其余区域自动关闭 popover  要求所有的Popover带有class pop
$('body').click(function (event) {
    var target = $(event.target);       // 判断自己当前点击的内容
    if (!target.hasClass('popover')
        && !target.hasClass('pop')
        && !target.hasClass('popover-content')
        && !target.hasClass('popover-title')
        && !target.hasClass('arrow')) {
        $('.pop').popover('hide');      // 当点击body的非弹出框相关的内容的时候，关闭所有popover
    }
});

function createBootstrapTable(table, toolbar) {
    init(table, toolbar);
}

function init(table, toolbar) {
    $(table).bootstrapTable({
        toolbar: toolbar,                   //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                    //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 8,                       //每页的记录行数（*）
        pageList: [8, 10, 20, 50],            //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,                 //严格搜索
        searchOnEnterKey: false,                //enter 搜索还是 自动搜索
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
            },
            {
                field: "date",
                title: "时代",
                visible: false
            },
            {
                field: "address",
                title: "地理位置",
                visible: false
            },
            {
                field: "comments",
                title: "备注",
                visible: false
            }
        ],
        refresh:function () {
            
        }
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
            itemsTable = eval(data);
            $("#table").bootstrapTable("load", itemsTable);
            lastSearchContent = searchContent;
        },
        error: function () {
            alert("表数据加载错误");
        }
    })
}

var lastSearchContent;  //上一次搜索成功时候的搜索内容，在每次点击搜索的时候 更新
function refreshData() {
    $.ajax({
        type: 'get',
        url: "http://localhost:8080/web/getlist?searchContent=" + lastSearchContent,
        success: function (data) {
            itemsTable = eval(data);
            $("#table").bootstrapTable("load", itemsTable);
        },
        error: function () {
            alert("表数据加载错误");
        }
    })
}

function addModalClear() {
    $("#addId").val("");
    $("#addName").val("");
    $("#addDepartment").val("");
    $("#addDescription").val("");
    $("#addAddress").val("");
    $("#addComments").val("");
    $("#addDate").val("");
}

function modifyModalClear() {
    $("#editId").val("");
    $("#editName").val("");
    $("#editDepartment").val("");
    $("#editDescription").val("");
    $("#editAddress").val("");
    $("#editComments").val("");
    $("#editDate").val("");
}
