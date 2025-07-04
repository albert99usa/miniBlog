<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class='container main'>
    <div class='col-md-9'>
        <ul class='breadcrumb'>
            <li><a href='${contextPath}/'><i class="glyphicon glyphicon-home"></i>主頁</a><span class='divider'></span></li>
            <li><a href='javascript:;'>標籤:${tagName}(${pager.totalElements}篇文章)</a><span class='divider'></span></li>
        </ul>
        <div class="panel">
            <div class="inner no-padding">
                <c:if test="${not empty pager.content}">
                    <div class="search-result">
                        <ul class="post-list">
                        <c:forEach items="${pager.content}" var="topic">
                            <li class="post-item">
                                <div class="entry">
                                    <a class="entry-link" href="${contextPath}/article/${topic.id}">
                                        <div class="content-box">
                                            <div class="info-box">
                                                <div class="meta-row">
                                                    <ul class="meta-list">
                                                        <c:if test="${'all'==indexVo.tab and topic.top}">
                                                            <li class="item recommended">置頂</li>
                                                        </c:if>
                                                        <li class="item username">
                                                            <a href="${contextPath}/pub/user/${topic.authorId}">${topic.authorName}.${topic.friendlyTime}</a>
                                                        </li>
                                                        <li class="item category">
                                                            <a href="${contextPath}/?tab=${topic.catDir}">
                                                                <span class="topiclist-tab">${topic.catName}</span>
                                                            </a>
                                                        </li>
                                                        <c:if test="${not empty topic.tags}">
                                                            <li class="item tag tag-box">
                                                                <c:forEach items="${topic.tags}" var="tag">
                                                                    <a class='tag <c:if test="${tag==tagName}">highlight</c:if>' href="${contextPath}/tag/${tag}">${tag}</a>
                                                                </c:forEach>
                                                            </li>
                                                        </c:if>
                                                    </ul>
                                                </div>
                                                <div class="title-row">
                                                    <a class="title" href="${contextPath}/article/${topic.id}" target="_blank">${topic.title}</a>
                                                </div>
                                                <div class="desc-row">${topic.desc}</div>
                                                <div class="action-row">
                                                    <div class="action-list">
                                                        <a class="action like" href="javascript:;" >
                                                            <span class="icon"></span>
                                                            <span class="title">${topic.visitCount}</span>
                                                        </a>
                                                        <a class="action comment" href="javascript:;" >
                                                            <span class="icon"></span>
                                                            <span class="title">${topic.replyCount}</span>
                                                        </a>
                                                        <a  class="action collect hover" href="javascript:;">
                                                            <span class="icon"></span>
                                                            <span class="title">收藏</span>
                                                        </a>
                                                        <a class="action share hover" href="javascript:;">
                                                            <span class="icon"></span>
                                                            <span class="title">分享</span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:if test="${not empty topic.thumbURL}">
                                                <div class="thumb-box" style='background-image: url("${topic.thumbURL}"); background-size: cover;'></div>
                                            </c:if>
                                        </div>
                                    </a>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                    <!--分頁开始-->
                    <jsp:include page="../inc/pagination.jsp">
                        <jsp:param name="pager" value="${pager}"/>
                        <jsp:param name="baseURL" value="${contextPath}/tag/${tagName}"/>
                        <jsp:param name="otherParams" value="&keywords=${tagName}"/>
                    </jsp:include>
                    <!--分頁结束-->
                </c:if>
                <c:if test="${empty pager.content}">
                    <p class="text-center">没有搜尋结果</p>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>
<script src="${contextPath}/js/mark/mark.js"></script>
<%--<script>--%>
    <%--$(document).ready(function(){--%>
        <%--var instance = new Mark(document.querySelector("div.search-result"));--%>
        <%--instance.mark("${tagName}", {--%>
            <%--"element": "span",--%>
            <%--"className": "highlight"--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>
