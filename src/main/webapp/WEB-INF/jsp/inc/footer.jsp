<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<div id='footer'>
    <div id='footer_main'>
        <div>miniBlog 一個簡單的個人部落格</div>
    </div>
</div>
<!-- scripts -->
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${contextPath}/static/js/jquery.scrollUp.min.js"></script>
<script src="${contextPath}/static/js/editormd/editormd.min.js"></script>
<!--回到顶部开始-->
<script>
    $(document).ready(function () {
        $.scrollUp({
            scrollName: 'scrollUp',
            topDistance: '100',
            topSpeed: 300,
            animation: 'fade',
            animationInSpeed: 200,
            animationOutSpeed: 200,
            scrollText: '',
            activeOverlay: false
        });
    });
</script>
<a id="scrollUp" href="#top" title="" style="position: fixed; z-index: 222222222222; display: none;"></a></body>
<!--回到顶部结束-->
</body>
</html>