<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class='container main'>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
    <div class='col-md-9'>
        <ul class='breadcrumb'>
            <li><a href='${contextPath}/'>主頁</a><span class='divider'></span></li>
            <li><a href='${contextPath}/cat/list'>管理分類</a><span class='divider'></span></li>
            <li class='active'>新建分類</li>
        </ul>
        <div class='panel'>
            <div class='inner'>
                <div class="topic_content">
                    <div class="markdown-text">
                        <h3>新建分類</h3>
                        <div class="row">
                            <jsp:include page="../inc/msgbox.jsp"></jsp:include>
                            <form method="post" action="${contextPath}/cat/create">
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">分類名稱:</div>
                                        <input type="text" class="form-control" name="catName" value="${cat.catName}" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">目錄名稱（小寫英文字母）:</div>
                                        <input type="text" class="form-control" name="catDir" value="${cat.catDir}" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">目錄简介:</div>
                                        <textarea name="catDesc" class="form-control" rows="4" placeholder="请输入分類简介，方便SEO优化">${cat.catDesc}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <button class="btn btn-success" type="submit">新建</button>
                                        <button class="btn btn-default" type="reset">清空</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>