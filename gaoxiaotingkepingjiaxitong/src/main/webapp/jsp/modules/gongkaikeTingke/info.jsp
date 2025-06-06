<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="../../static/head.jsp" %>
    <link href="http://www.bootcss.com/p/bootstrap-datetimepicker/bootstrap-datetimepicker/css/datetimepicker.css"
          rel="stylesheet">
    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "${pageContext.request.contextPath}/resources/ueditor/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/resources/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/resources/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/resources/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- layui -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
</head>
<style>

</style>
<body>
<!-- Pre Loader -->
<div class="loading">
    <div class="spinner">
        <div class="double-bounce1"></div>
        <div class="double-bounce2"></div>
    </div>
</div>
<!--/Pre Loader -->
<div class="wrapper">
    <!-- Page Content -->
    <div id="content">
        <!-- Top Navigation -->
        <%@ include file="../../static/topNav.jsp" %>
        <!-- Menu -->
        <div class="container menu-nav">
            <nav class="navbar navbar-expand-lg lochana-bg text-white">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="ti-menu text-white"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto" id="navUl">

                    </ul>
                </div>
            </nav>
        </div>
        <!-- /Menu -->
        <!-- Breadcrumb -->
        <!-- Page Title -->
        <div class="container mt-0">
            <div class="row breadcrumb-bar">
                <div class="col-md-6">
                    <h3 class="block-title">编辑公开课听课</h3>
                </div>
                <div class="col-md-6">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/index.jsp">
                                <span class="ti-home"></span>
                            </a>
                        </li>
                        <li class="breadcrumb-item">公开课听课管理</li>
                        <li class="breadcrumb-item active">公开课听课登记</li>
                    </ol>
                </div>
            </div>
        </div>
        <!-- /Page Title -->

        <!-- /Breadcrumb -->
        <!-- Main Content -->
        <div class="container">

            <div class="row">
                <!-- Widget Item -->
                <div class="col-md-12">
                    <div class="widget-area-2 lochana-box-shadow">
                        <h3 class="widget-title">公开课听课信息</h3>
                        <form id="addOrUpdateForm">
                            <div class="form-row">
                            <!-- 级联表所有字段 -->

                                        <div class="form-group col-md-6">
                                            <label>公开课编号</label>
                                            <input style="width: 450px" id="gongkaikeUuidNumber" name="gongkaikeUuidNumber" class="form-control"
                                                   v-model="ruleForm.gongkaikeUuidNumber" readonly>
                                        </div>


                                        <div class="form-group col-md-6">
                                            <label>公开课名称</label>
                                            <input style="width: 450px" id="gongkaikeName" name="gongkaikeName" class="form-control"
                                                   v-model="ruleForm.gongkaikeName" readonly>
                                        </div>


                                    <div class="form-group col-md-6">
                                        <label>公开课类型</label>
                                        <input style="width: 450px" id="gongkaikeValue" name="gongkaikeValue" class="form-control"
                                               v-model="ruleForm.gongkaikeValue" readonly>
                                    </div>



                                        <div class="form-group col-md-6">
                                            <label>督导老师工号</label>
                                            <input style="width: 450px" id="dudaolaoshiUuidNumber" name="dudaolaoshiUuidNumber" class="form-control"
                                                   v-model="ruleForm.dudaolaoshiUuidNumber" readonly>
                                        </div>


                                        <div class="form-group col-md-6">
                                            <label>督导老师姓名</label>
                                            <input style="width: 450px" id="dudaolaoshiName" name="dudaolaoshiName" class="form-control"
                                                   v-model="ruleForm.dudaolaoshiName" readonly>
                                        </div>


                                        <div class="form-group col-md-6">
                                            <label>督导老师手机号</label>
                                            <input style="width: 450px" id="dudaolaoshiPhone" name="dudaolaoshiPhone" class="form-control"
                                                   v-model="ruleForm.dudaolaoshiPhone" readonly>
                                        </div>


                                    <div class="form-group col-md-6">
                                        <label>督导老师头像</label>
                                        <img id="dudaolaoshiPhotoImg" width="100" height="100">
                                    </div>
                            <!-- 本表所有字段 -->



                                    <input id="updateId" name="id" type="hidden">
                                <input id="dudaolaoshiId" name="dudaolaoshiId" v-model="ruleForm.dudaolaoshiId" readonly type="hidden">
                                <input id="gongkaikeId" name="gongkaikeId" v-model="ruleForm.gongkaikeId" readonly type="hidden">

                                    <div class="form-group col-md-6">
                                        <label>申请编号</label>
                                        <input style="width: 450px" id="gongkaikeTingkeUuidNumber" name="gongkaikeTingkeUuidNumber" class="form-control"
                                               v-model="ruleForm.gongkaikeTingkeUuidNumber" readonly>
                                    </div>


                                    <div class="form-group col-md-6">
                                        <label>评分</label>
                                        <div id="gongkaikeTingkePingfenDiv"></div>
                                        <%--<input style="width: 450px" id="gongkaikeTingkePingfen" name="gongkaikeTingkePingfen" class="form-control"--%>
                                               <%--v-model="ruleForm.gongkaikeTingkePingfen" readonly>--%>
                                    </div>


                                <div>
                                    <label>评价内容</label>
                                    <script id="gongkaikeTingkeContentEditor" type="text/plain"
                                            style="width:800px;height:230px;"></script>
                                    <script type = "text/javascript" >
                                    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
                                    //相见文档配置属于你自己的编译器
                                    var gongkaikeTingkeContentUe = UE.getEditor('gongkaikeTingkeContentEditor', {
                                        toolbars: [
                                            [
                                                'undo', //撤销
                                                'bold', //加粗
                                                'redo', //重做
                                                'underline', //下划线
                                                'horizontal', //分隔线
                                                'inserttitle', //插入标题
                                                'cleardoc', //清空文档
                                                'fontfamily', //字体
                                                'fontsize', //字号
                                                'paragraph', //段落格式
                                                'inserttable', //插入表格
                                                'justifyleft', //居左对齐
                                                'justifyright', //居右对齐
                                                'justifycenter', //居中对
                                                'justifyjustify', //两端对齐
                                                'forecolor', //字体颜色
                                                'fullscreen', //全屏
                                                'edittip ', //编辑提示
                                                'customstyle', //自定义标题
                                            ]
                                        ]
                                    });
                                    </script>
                                    <input type="hidden" name="gongkaikeTingkeContent" id="gongkaikeTingkeContent-input">
                                </div>

                                <div class="form-group col-md-12 mb-3">
                                    <button id="exitBtn" type="button" class="btn btn-primary btn-lg">返回</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- /Widget Item -->
            </div>
        </div>
        <!-- /Main Content -->
    </div>
    <!-- /Page Content -->
</div>
<!-- Back to Top -->
<a id="back-to-top" href="#" class="back-to-top">
    <span class="ti-angle-up"></span>
</a>
<!-- /Back to Top -->
<%@ include file="../../static/foot.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>

<script>
    <%@ include file="../../utils/menu.jsp"%>
            <%@ include file="../../static/setMenu.js"%>
            <%@ include file="../../utils/baseUrl.jsp"%>

    var tableName = "gongkaikeTingke";
    var pageType = "add-or-update";
    var updateId = "";


    var ruleForm = {};
    var vm = new Vue({
        el: '#addOrUpdateForm',
        data: {
            ruleForm: {},
        },
        beforeCreate: function () {
            var id = window.sessionStorage.getItem("updateId");
            if (id != null && id != "" && id != "null") {
                $.ajax({
                    type: "GET",
                    url: baseUrl + "gongkaikeTingke/info/" + id,
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("token", window.sessionStorage.getItem('token'));
                    },
                    success: function (res) {
                        if (res.code == 0) {
                            vm.ruleForm = res.data;
                            ruleForm = res.data;
                            layui.use(['rate'], function () {
                                var rate = layui.rate;//评分
                                rate.render({
                                    elem: '#gongkaikeTingkePingfenDiv'
                                    ,value: vm.ruleForm.gongkaikeTingkePingfen //初始值
                                    ,text: true //开启文本
                                    ,readonly:true
                                });

                            });
                            showImg();
                            setContent();
                        } else if (res.code == 401) {
                        <%@ include file="../../static/toLogin.jsp"%>
                        } else {
                            alert(res.msg)
                        }
                    },
                });
            }
        },
        methods: {}
    });


    // 填充富文本框
    function setContent() {

        if (ruleForm.gongkaikeTingkeContent != null && ruleForm.gongkaikeTingkeContent != 'null' && ruleForm.gongkaikeTingkeContent != '') {
            var gongkaikeTingkeContentUeditor = UE.getEditor('gongkaikeTingkeContentEditor');
                gongkaikeTingkeContentUeditor.ready(function () {
                    var mes = '';
                    if(ruleForm.gongkaikeTingkeContent != null){
                        mes = ''+ ruleForm.gongkaikeTingkeContent;
                        // mes = mes.replace(/\n/g, "<br>");
                    }
                    gongkaikeTingkeContentUeditor.setContent(mes);
                    gongkaikeTingkeContentUeditor.setDisabled('fullscreen');
            });
        }
    }

    //图片显示
    function showImg() {
        $("#dudaolaoshiPhotoImg").attr("src", ruleForm.dudaolaoshiPhoto);
    }

    function exit() {
        window.sessionStorage.removeItem("updateId");
        window.sessionStorage.removeItem('addgongkaikeTingke');
        window.location.href = "list.jsp";
    }

    // 下载
    function download(fileName) {
        var url = baseUrl+'file/download?fileName='+fileName;
        window.open(url);
    }

    //设置下载
    function setDownloadBtn() {

    }


    $(document).ready(function () {
        //设置右上角用户名
        $('.dropdown-menu h5').html(window.sessionStorage.getItem('username'))
        //设置项目名
        $('.sidebar-header h3 a').html(projectName)
        setMenu();
        $('#exitBtn').on('click', function (e) {
            e.preventDefault();
            exit();
        });


    <%@ include file="../../static/myInfo.js"%>
    });
    // 用户登出
    <%@ include file="../../static/logout.jsp"%>
</script>
</body>

</html>
