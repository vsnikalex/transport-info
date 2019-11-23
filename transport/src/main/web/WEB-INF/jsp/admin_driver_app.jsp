<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Transport-Info</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- syntax highlighting CSS -->
        <link href="<c:url value="/resources/css/syntax.css" />" rel="stylesheet">

        <!-- Components CSS -->
        <link href="<c:url value="/resources/css/components.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/theme-dark.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/theme-bevel.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/theme-debug.min.css" />" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="main" style="min-height: calc(100vh - 249px);">
            <div class="breadcrumbbar">
                <div class="container-fixed">
                    <ol class="breadcrumb breadcrumb-small">
                        <li>Manager Page</li>
                        <li class="active">Routes</li>
                    </ol>
                </div>
            </div>
            <div class="container-fixed">
                <div class="main">
                    <div id="driver_app"></div>
                    <script src="<c:url value="/resources/js/webpack/DriverApp.bundle.js" />"></script>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />

        <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/js/components.min.js" />"></script>
        <script src="<c:url value="/resources/js/docs.js" />"></script>

    </body>
</html>
