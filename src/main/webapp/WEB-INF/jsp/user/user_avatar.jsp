<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class="container main">
    <div class="row">
        <div class="col-md-3 left sidebar">
            <jsp:include page="user_sidebar.jsp">
                <jsp:param name="activeTab" value="changeavatar"/>
            </jsp:include>
        </div>
        <div class="col-md-9">
            <ul class="breadcrumb">
                <li><a href="${contextPath}/"><i class="glyphicon glyphicon-home"></i> 首頁</a></li>
                <li class="active">修改頭像</li>
            </ul>
            <div class="wrapper">
                <jsp:include page="../inc/msgbox.jsp"></jsp:include>
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#upload_vatar" data-toggle="tab">上傳頭像</a></li>
                    <li class=""><a href="#get_avatar" data-toggle="tab">从Avatar獲取</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade active in" id="upload_vatar">
                        <br>
                        <form action="${contextPath}/user/changeAvatar" method="post" enctype="multipart/form-data" class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-lg-2" for="file">選擇圖片文件</label>
                                <div class="col-lg-10">
                                    <input type="file" name="file" id="file">
                                    <span class="help-block">支持 500K 以内的 jpg/png 文件</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                    <input type="hidden" name="uid" value="${loginUser.id}">
                                    <button type="submit" class="btn btn-success">上傳新頭像</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="get_avatar">
                        <br>
                        <a href="${contextPath}/user/${loginUser.username}/getAvatar/${loginUser.email}" class="btn btn-success">獲取</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>