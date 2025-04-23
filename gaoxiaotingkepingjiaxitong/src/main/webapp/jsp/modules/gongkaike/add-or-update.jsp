<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="../../static/head.jsp" %>
    <link href="http://www.bootcss.com/p/bootstrap-datetimepicker/bootstrap-datetimepicker/css/datetimepicker.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
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
    .error {
        color: red;
    }
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
                    <ul id="navUl" class="navbar-nav mr-auto">

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
                    <h3 class="block-title">编辑公开课</h3>
                </div>
                <div class="col-md-6">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/index.jsp">
                                <span class="ti-home"></span>
                            </a>
                        </li>
                        <li class="breadcrumb-item">公开课管理</li>
                        <li class="breadcrumb-item active">编辑公开课</li>
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
                        <h3 class="widget-title">公开课信息</h3>
                        <form id="addOrUpdateForm">
                            <div class="form-row">
                            <!-- 级联表的字段 -->
                                    <div class="form-group col-md-6 aaaaaa putonglaoshi">
                                        <label>普通老师</label>
                                        <div>
                                            <select style="width: 450px" id="putonglaoshiSelect" name="putonglaoshiSelect"
                                                    class="selectpicker form-control"  data-live-search="true"
                                                    title="请选择" data-header="请选择" data-size="5" data-width="650px">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6 putonglaoshi">
                                        <label>普通老师工号</label>
                                        <input style="width: 450px" id="putonglaoshiUuidNumber" name="putonglaoshiUuidNumber" class="form-control"
                                               placeholder="普通老师工号" readonly>
                                    </div>
                                    <div class="form-group col-md-6 putonglaoshi">
                                        <label>普通老师姓名</label>
                                        <input style="width: 450px" id="putonglaoshiName" name="putonglaoshiName" class="form-control"
                                               placeholder="普通老师姓名" readonly>
                                    </div>
                                    <div class="form-group col-md-6 putonglaoshi">
                                        <label>普通老师手机号</label>
                                        <input style="width: 450px" id="putonglaoshiPhone" name="putonglaoshiPhone" class="form-control"
                                               placeholder="普通老师手机号" readonly>
                                    </div>
                                    <div class="form-group col-md-6 putonglaoshi">
                                        <label>普通老师头像</label>
                                        <img id="putonglaoshiPhotoImg" src="" width="100" height="100">
                                    </div>
                            <!-- 当前表的字段 -->
                                    <input id="updateId" name="id" type="hidden">
                                <input id="putonglaoshiId" name="putonglaoshiId" type="hidden">
                                    <div class="form-group col-md-6 gongkaikeUuidNumberDiv">
                                        <label>公开课编号</label>
                                        <input style="width: 450px" id="gongkaikeUuidNumber" name="gongkaikeUuidNumber" class="form-control"
                                               placeholder="公开课编号">
                                    </div>
                                    <div class="form-group col-md-6 gongkaikeNameDiv">
                                        <label>公开课名称</label>
                                        <input style="width: 450px" id="gongkaikeName" name="gongkaikeName" class="form-control"
                                               placeholder="公开课名称">
                                    </div>
                                    <div class="form-group col-md-6 gongkaikeTypesDiv">
                                        <label>公开课类型</label>
                                        <select style="width: 450px" id="gongkaikeTypesSelect" name="gongkaikeTypes" class="form-control">
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6 banjiTypesDiv">
                                        <label>授课班级</label>
                                        <select style="width: 450px" id="banjiTypesSelect" name="banjiTypes" class="form-control">
                                        </select>
                                    </div>
                                    <div class="form-group  col-md-6 gongkaikeContentDiv">
                                        <label>章节内容</label>
                                        <input id="gongkaikeContentupload" name="file" type="file">
                                        <script id="gongkaikeContentEditor" type="text/plain"
                                                style="width:100%;height:230px;"></script>
                                        <script type = "text/javascript" >
                                        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
                                        //相见文档配置属于你自己的编译器
                                        var gongkaikeContentUe = UE.getEditor('gongkaikeContentEditor', {
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
                                        <input type="hidden" name="gongkaikeContent" id="gongkaikeContent-input">
                                    </div>
                                    <div class="form-group col-md-6 gongkaikeShichangDiv">
                                        <label>公开课预估时长</label>
                                        <input style="width: 450px" id="gongkaikeShichang" name="gongkaikeShichang" class="form-control"
                                               placeholder="公开课预估时长">
                                    </div>
                                    <div class="form-group col-md-6 gongkaikeAddressDiv">
                                        <label>公开课开课位置</label>
                                        <input style="width: 450px" id="gongkaikeAddress" name="gongkaikeAddress" class="form-control"
                                               placeholder="公开课开课位置">
                                    </div>
                                    <div class="form-group col-md-6 gongkaikeNumberDiv">
                                        <label>最大听课人数</label>
                                        <input style="width: 450px" id="gongkaikeNumber" name="gongkaikeNumber" class="form-control"
                                               onchange="gongkaikeNumberChickValue(this)"  placeholder="最大听课人数">
                                    </div>

                                    <div class="form-group col-md-6 gongkaikePingfenDiv">
                                        <label>公开课评分</label>
                                        <div id="gongkaikePingfenDiv1"></div>
                                        <input type="hidden" style="width: 450px" id="gongkaikePingfen" name="gongkaikePingfen" class="form-control"
                                               onchange="gongkaikePingfenChickValue(this)"  placeholder="公开课评分">
                                    </div>

                                    <div class="form-group col-md-6 kaikeTimeDiv">
                                        <label>开课时间</label>
                                        <input style="width: 450px" id="kaikeTime-input" name="kaikeTime" type="text" class="form-control layui-input">
                                    </div>
                                    <div class="form-group col-md-12 mb-3">
                                        <button id="submitBtn" type="button" class="btn btn-primary btn-lg">提交</button>
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
<script src="${pageContext.request.contextPath}/resources/js/jquery.fileupload.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/resources/js/validate/messages_zh.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/resources/js/validate/card.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/resources/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" charset="utf-8"
                 src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js"></script>
<script>
    <%@ include file="../../utils/menu.jsp"%>
    <%@ include file="../../static/setMenu.js"%>
    <%@ include file="../../utils/baseUrl.jsp"%>

    var tableName = "gongkaike";
    var pageType = "add-or-update";
    var updateId = "";
    var crossTableId = -1;
    var crossTableName = '';
    var ruleForm = {};
    var crossRuleForm = {};


    // 下拉框数组
        <!-- 当前表的下拉框数组 -->
    var gongkaikeTypesOptions = [];
    var banjiTypesOptions = [];
        <!-- 级联表的下拉框数组 -->
    var putonglaoshiOptions = [];

    var ruleForm = {};


    // 文件上传
    function upload() {

        <!-- 当前表的文件上传 -->

        $('#gongkaikeContentupload').fileupload({
            url: baseUrl + 'file/upload',
            headers: {token: window.sessionStorage.getItem("token")},
            dataType: 'json',
            done: function (e, data) {
                UE.getEditor('gongkaikeContentEditor').execCommand('insertHtml', '<img src=\"' + baseUrl + 'upload/' + data.result.file + '\" width=900 height=560>');
            }
        });


    }

    // 表单提交
    function submit() {
        if (validform() == true && compare() == true) {
            let data = {};
            getContent();
            if(window.sessionStorage.getItem('role') != '普通老师'){//当前登录用户不为这个
                if($("#putonglaoshiId") !=null){
                    var putonglaoshiId = $("#putonglaoshiId").val();
                    if(putonglaoshiId == null || putonglaoshiId =='' || putonglaoshiId == 'null'){
                        alert("普通老师不能为空");
                        return;
                    }
                }
            }
            let value = $('#addOrUpdateForm').serializeArray();
            $.each(value, function (index, item) {
                data[item.name] = item.value;
            });
            let json = JSON.stringify(data);
            var urlParam;
            var successMes = '';
            if (updateId != null && updateId != "null" && updateId != '') {
                urlParam = 'update';
                successMes = '修改成功';
            } else {
                urlParam = 'save';
                    successMes = '添加成功';

            }
            httpJson("gongkaike/" + urlParam, "POST", data, (res) => {
                if(res.code == 0){
                    window.sessionStorage.removeItem('addgongkaike');
                    window.sessionStorage.removeItem('updateId');
                    let flag = true;
                    if (flag) {
                        alert(successMes);
                    }
                    if (window.sessionStorage.getItem('onlyme') != null && window.sessionStorage.getItem('onlyme') == "true") {
                        window.sessionStorage.removeItem('onlyme');
                        window.sessionStorage.setItem("reload","reload");
                        window.parent.location.href = "${pageContext.request.contextPath}/index.jsp";
                    } else {
                        window.location.href = "list.jsp";
                    }
                }
            });
        } else {
            alert("表单未填完整或有错误");
        }
    }

    // 查询列表
        <!-- 查询当前表的所有列表 -->
        function gongkaikeTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=gongkaike_types", "GET", {}, (res) => {
                if(res.code == 0){
                    gongkaikeTypesOptions = res.data.list;
                }
            });
        }
        function banjiTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=banji_types", "GET", {}, (res) => {
                if(res.code == 0){
                    banjiTypesOptions = res.data.list;
                }
            });
        }
        <!-- 查询级联表的所有列表 -->
        function putonglaoshiSelect() {
            //填充下拉框选项
            http("putonglaoshi/page?page=1&limit=100&sort=&order=", "GET", {}, (res) => {
                if(res.code == 0){
                    putonglaoshiOptions = res.data.list;
                }
            });
        }

        function putonglaoshiSelectOne(id) {
            http("putonglaoshi/info/"+id, "GET", {}, (res) => {
                if(res.code == 0){
                ruleForm = res.data;
                putonglaoshiShowImg();
                putonglaoshiShowVideo();
                putonglaoshiDataBind();
            }
        });
        }



    // 初始化下拉框
    <!-- 初始化当前表的下拉框 -->
        function initializationGongkaiketypesSelect(){
            var gongkaikeTypesSelect = document.getElementById('gongkaikeTypesSelect');
            if(gongkaikeTypesSelect != null && gongkaikeTypesOptions != null  && gongkaikeTypesOptions.length > 0 ){
                for (var i = 0; i < gongkaikeTypesOptions.length; i++) {
                    gongkaikeTypesSelect.add(new Option(gongkaikeTypesOptions[i].indexName,gongkaikeTypesOptions[i].codeIndex));
                }
            }
        }
        function initializationBanjitypesSelect(){
            var banjiTypesSelect = document.getElementById('banjiTypesSelect');
            if(banjiTypesSelect != null && banjiTypesOptions != null  && banjiTypesOptions.length > 0 ){
                for (var i = 0; i < banjiTypesOptions.length; i++) {
                    banjiTypesSelect.add(new Option(banjiTypesOptions[i].indexName,banjiTypesOptions[i].codeIndex));
                }
            }
        }

        function initializationputonglaoshiSelect() {
            var putonglaoshiSelect = document.getElementById('putonglaoshiSelect');
            if(putonglaoshiSelect != null && putonglaoshiOptions != null  && putonglaoshiOptions.length > 0 ) {
                for (var i = 0; i < putonglaoshiOptions.length; i++) {
                        putonglaoshiSelect.add(new Option(putonglaoshiOptions[i].putonglaoshiName, putonglaoshiOptions[i].id));
                }

                $("#putonglaoshiSelect").change(function(e) {
                        putonglaoshiSelectOne(e.target.value);
                });
            }

        }



    // 下拉框选项回显
    function setSelectOption() {

        <!-- 当前表的下拉框回显 -->

        var gongkaikeTypesSelect = document.getElementById("gongkaikeTypesSelect");
        if(gongkaikeTypesSelect != null && gongkaikeTypesOptions != null  && gongkaikeTypesOptions.length > 0 ) {
            for (var i = 0; i < gongkaikeTypesOptions.length; i++) {
                if (gongkaikeTypesOptions[i].codeIndex == ruleForm.gongkaikeTypes) {//下拉框value对比,如果一致就赋值汉字
                        gongkaikeTypesSelect.options[i].selected = true;
                }
            }
        }

        var banjiTypesSelect = document.getElementById("banjiTypesSelect");
        if(banjiTypesSelect != null && banjiTypesOptions != null  && banjiTypesOptions.length > 0 ) {
            for (var i = 0; i < banjiTypesOptions.length; i++) {
                if (banjiTypesOptions[i].codeIndex == ruleForm.banjiTypes) {//下拉框value对比,如果一致就赋值汉字
                        banjiTypesSelect.options[i].selected = true;
                }
            }
        }
        <!--  级联表的下拉框回显  -->
            var putonglaoshiSelect = document.getElementById("putonglaoshiSelect");
            if(putonglaoshiSelect != null && putonglaoshiOptions != null  && putonglaoshiOptions.length > 0 ) {
                for (var i = 0; i < putonglaoshiOptions.length; i++) {
                    if (putonglaoshiOptions[i].id == ruleForm.putonglaoshiId) {//下拉框value对比,如果一致就赋值汉字
                        putonglaoshiSelect.options[i+1].selected = true;
                        $("#putonglaoshiSelect" ).selectpicker('refresh');
                    }
                }
            }
    }


    // 填充富文本框
    function setContent() {

        <!-- 当前表的填充富文本框 -->
        if (ruleForm.gongkaikeContent != null && ruleForm.gongkaikeContent != 'null' && ruleForm.gongkaikeContent != '' && $("#gongkaikeContentupload").length>0) {

            var gongkaikeContentUeditor = UE.getEditor('gongkaikeContentEditor');
            gongkaikeContentUeditor.ready(function () {
                var mes = '';
                if(ruleForm.gongkaikeContent != null){
                    mes = ''+ ruleForm.gongkaikeContent;
                    // mes = mes.replace(/\n/g, "<br>");
                }
                gongkaikeContentUeditor.setContent(mes);
            });
        }
    }
    // 获取富文本框内容
    function getContent() {

        <!-- 获取当前表的富文本框内容 -->
        if($("#gongkaikeContentupload").length>0) {
            var gongkaikeContentEditor = UE.getEditor('gongkaikeContentEditor');
            if (gongkaikeContentEditor.hasContents()) {
                $('#gongkaikeContent-input').attr('value', gongkaikeContentEditor.getContent());
            }
        }
    }
    //数字检查
        <!-- 当前表的数字检查 -->
        function gongkaikeNumberChickValue(e){
            var this_val = e.value || 0;
            var reg=/^[1-9][0-9]*$/;
            if(!reg.test(this_val)){
                e.value = "";
                alert("输入不合法");
                return false;
            }
        }
        function gongkaikePingfenChickValue(e){
            var this_val = e.value || 0;
            var reg=/^[0-9]{0,6}(\.[0-9]{1,2})?$/;
            if(!reg.test(this_val)){
                e.value = "";
                alert("只允许输入整数6位,小数2位的数字");
                return false;
            }
        }

    function exit() {
        window.sessionStorage.removeItem("updateId");
        window.sessionStorage.removeItem('addgongkaike');
        window.location.href = "list.jsp";
    }
    // 表单校验
    function validform() {
        return $("#addOrUpdateForm").validate({
            rules: {
                putonglaoshiId: "required",
                gongkaikeUuidNumber: "required",
                gongkaikeName: "required",
                gongkaikeTypes: "required",
                banjiTypes: "required",
                gongkaikeContent: "required",
                gongkaikeShichang: "required",
                gongkaikeAddress: "required",
                gongkaikeNumber: "required",
                gongkaikePingfen: "required",
                kaikeTime: "required",
            },
            messages: {
                putonglaoshiId: "普通老师不能为空",
                gongkaikeUuidNumber: "公开课编号不能为空",
                gongkaikeName: "公开课名称不能为空",
                gongkaikeTypes: "公开课类型不能为空",
                banjiTypes: "授课班级不能为空",
                gongkaikeContent: "章节内容不能为空",
                gongkaikeShichang: "公开课预估时长不能为空",
                gongkaikeAddress: "公开课开课位置不能为空",
                gongkaikeNumber: "最大听课人数不能为空",
                gongkaikePingfen: "公开课评分不能为空",
                kaikeTime: "开课时间不能为空",
            }
        }).form();
    }

    // 获取当前详情
    function getDetails() {
        var addgongkaike = window.sessionStorage.getItem("addgongkaike");
        if (addgongkaike != null && addgongkaike != "" && addgongkaike != "null") {
            //注册表单验证
            $(validform());
            $("#gongkaikeUuidNumber").val(new Date().getTime()+Math.ceil(Math.random()*10));//设置唯一号

            $('#submitBtn').text('新增');
            layui.use(['jquery', 'rate', 'laydate'], function () {
                var rate = layui.rate;//评分
                var laydate = layui.laydate;//laydate
                var jquery = layui.jquery;//jquery
                var $=jquery;

                $("#gongkaikePingfen").val(3);
                rate.render({
                    elem: '#gongkaikePingfenDiv1'
                    ,value: 3 //初始值
                    ,text: true //开启文本
                    ,choose:function(value){
                        $("#gongkaikePingfen").val(value);
                    }
                });


                var kaikeTime = laydate.render({
                    elem: '#kaikeTime-input'
                    ,type: 'datetime'
                });
                var insertTime = laydate.render({
                    elem: '#insertTime-input'
                    ,type: 'datetime'
                });
            });

        } else {
            $('#submitBtn').text('修改');
            var userId = window.sessionStorage.getItem('userId');
            updateId = userId;//先赋值登录用户id
            var uId  = window.sessionStorage.getItem('updateId');//获取修改传过来的id
            if (uId != null && uId != "" && uId != "null") {
                //如果修改id不为空就赋值修改id
                updateId = uId;
            }
            window.sessionStorage.removeItem('updateId');
            http("gongkaike/info/" + updateId, "GET", {}, (res) => {
                if(res.code == 0)
                {
                    ruleForm = res.data
                    // 是/否下拉框回显
                    setSelectOption();
                    // 设置图片src
                    showImg();
                    // 设置视频src
                    showVideo();
                    // 数据填充
                    dataBind();


                    // if(window.sessionStorage.getItem("role")== '普通老师'){
					//
                    //     layui.use(['jquery', 'rate', 'laydate'], function () {
                    //         var rate = layui.rate;//评分
                    //         var jquery = layui.jquery;//jquery
                    //         var $=jquery;
                    //         $("#gongkaikePingfen").val(ruleForm.gongkaikePingfen);
                    //         rate.render({
                    //             elem: '#gongkaikePingfenDiv1'
                    //             ,value: ruleForm.gongkaikePingfen //初始值
                    //             ,text: true //开启文本
                    //             ,choose:function(value){
                    //                 $("#gongkaikePingfen").val(value);
                    //             }
                    //         });
                    //     });
                    // }else{
                        layui.use(['jquery', 'rate', 'laydate'], function () {
                            var rate = layui.rate;//评分
                            var laydate = layui.laydate;//laydate
                            var jquery = layui.jquery;//jquery
                            var $=jquery;
                            $("#gongkaikePingfen").val(ruleForm.gongkaikePingfen);
                            rate.render({
                                elem: '#gongkaikePingfenDiv1'
                                ,value: ruleForm.gongkaikePingfen //初始值
                                ,text: true //开启文本
                                ,choose:function(value){
                                    $("#gongkaikePingfen").val(value);
                                }
                            });


                            var kaikeTime = laydate.render({
                                elem: '#kaikeTime-input'
                                ,type: 'datetime'
                            });
                            var insertTime = laydate.render({
                                elem: '#insertTime-input'
                                ,type: 'datetime'
                            });
                        });
                    // }
                    // 富文本框回显
                    setContent();
                    //注册表单验证
                    $(validform());
                }

            });
        }
    }

    // 清除可能会重复渲染的selection
    function clear(className) {
        var elements = document.getElementsByClassName(className);
        for (var i = elements.length - 1; i >= 0; i--) {
            elements[i].parentNode.removeChild(elements[i]);
        }
    }

    function dateTimePick() {
    
    }


    function dataBind() {


    <!--  级联表的数据回显  -->
        putonglaoshiDataBind();


    <!--  当前表的数据回显  -->
        $("#updateId").val(ruleForm.id);
        $("#putonglaoshiId").val(ruleForm.putonglaoshiId);
        $("#gongkaikeUuidNumber").val(ruleForm.gongkaikeUuidNumber);
        $("#gongkaikeName").val(ruleForm.gongkaikeName);
        $("#gongkaikeContent").val(ruleForm.gongkaikeContent);
        $("#gongkaikeShichang").val(ruleForm.gongkaikeShichang);
        $("#gongkaikeAddress").val(ruleForm.gongkaikeAddress);
        $("#gongkaikeNumber").val(ruleForm.gongkaikeNumber);
        $("#gongkaikePingfen").val(ruleForm.gongkaikePingfen);
        $("#kaikeTime-input").val(ruleForm.kaikeTime);

    }
    <!--  级联表的数据回显  -->
    function putonglaoshiDataBind(){

                    <!-- 把id赋值给当前表的id-->
        $("#putonglaoshiId").val(ruleForm.id);

        $("#putonglaoshiUuidNumber").val(ruleForm.putonglaoshiUuidNumber);
        $("#putonglaoshiName").val(ruleForm.putonglaoshiName);
        $("#putonglaoshiPhone").val(ruleForm.putonglaoshiPhone);
        $("#putonglaoshiIdNumber").val(ruleForm.putonglaoshiIdNumber);
        $("#kemuValue").val(ruleForm.kemuValue);
        $("#laoshiPingfen").val(ruleForm.laoshiPingfen);
        $("#putonglaoshiEmail").val(ruleForm.putonglaoshiEmail);
    }


    //图片显示
    function showImg() {
        <!--  当前表的图片  -->

        <!--  级联表的图片  -->
        putonglaoshiShowImg();
    }


    <!--  级联表的图片  -->

    function putonglaoshiShowImg() {
        $("#putonglaoshiPhotoImg").attr("src",ruleForm.putonglaoshiPhoto);
    }



    //视频回显
    function showVideo() {
    <!--  当前表的视频  -->

    <!--  级联表的视频  -->
        putonglaoshiShowVideo();
    }


    <!--  级联表的视频  -->

    function putonglaoshiShowVideo() {
        $("#putonglaoshiPhotoV").attr("src",ruleForm.putonglaoshiPhoto);
    }



    $(document).ready(function () {
        //设置右上角用户名
        $('.dropdown-menu h5').html(window.sessionStorage.getItem('username'))
        //设置项目名
        $('.sidebar-header h3 a').html(projectName)
        //设置导航栏菜单
        setMenu();
        $('#exitBtn').on('click', function (e) {
            e.preventDefault();
            exit();
        });
        //查询所有下拉框
            <!--  当前表的下拉框  -->
            gongkaikeTypesSelect();
            banjiTypesSelect();
            <!-- 查询级联表的下拉框(用id做option,用名字及其他参数做名字级联修改) -->
            putonglaoshiSelect();



        // 初始化下拉框
            <!--  初始化当前表的下拉框  -->
            initializationGongkaiketypesSelect();
            initializationBanjitypesSelect();
            <!--  初始化级联表的下拉框  -->
            initializationputonglaoshiSelect();

        $(".selectpicker" ).selectpicker('refresh');
        getDetails();
        //初始化时间插件
        dateTimePick();
        //初始化上传按钮
        upload();
    <%@ include file="../../static/myInfo.js"%>
                $('#submitBtn').on('click', function (e) {
                    e.preventDefault();
                    //console.log("点击了...提交按钮");
                    submit();
                });
        readonly();
        window.sessionStorage.removeItem('addgongkaike');
    });

    function readonly() {
        if (window.sessionStorage.getItem('role') == '管理员') {
            //$('#jifen').attr('readonly', 'readonly');
            //$('#role').attr('style', 'pointer-events:none;width:450px;');
        }
		else if (window.sessionStorage.getItem('role') == '普通老师') {
            // $(".aaaaaa").remove();
            $(".putonglaoshi").remove();//删除当前用户的信息
            $(".gongkaikePingfenDiv").remove();//删除当前用户的信息
        }
		else if (window.sessionStorage.getItem('role') == '督导老师') {
            // $(".aaaaaa").remove();
            $(".dudaolaoshi").remove();//删除当前用户的信息
        }
        else{
            // alert("未知情况.......");
            // var replyTextUeditor = UE.getEditor('replyTextEditor');
            // replyTextUeditor.ready(function () {
            //     replyTextUeditor.setDisabled('fullscreen');
            // });
        }
    }

    //比较大小
    function compare() {
        var largerVal = null;
        var smallerVal = null;
        if (largerVal != null && smallerVal != null) {
            if (largerVal <= smallerVal) {
                alert(smallerName + '不能大于等于' + largerName);
                return false;
            }
        }
        return true;
    }


    // 用户登出
    <%@ include file="../../static/logout.jsp"%>
</script>
</body>

</html>
