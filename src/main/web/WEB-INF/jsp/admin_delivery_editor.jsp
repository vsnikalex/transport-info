<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Tabellen</title>
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

    <!-- Leaflet CSS -->
    <link href="<c:url value="/resources/css/leaflet/leaflet.css" />" rel="stylesheet">

</head>

<body>

<jsp:include page="header.jsp" />

<div class="main">
    <div class="breadcrumbbar">
        <div class="container-fixed">
            <ol class="breadcrumb breadcrumb-small">
                <li>Admin Page</li>
                <li class="active">Deliveries</li>
            </ol>
        </div>
    </div>

    <div class="container-fixed">
        <h1 class="underline">Delivery</h1>

        <div class="row">
            <div class="demo-col col-xs-12 col-s-6 col-l-8 text-ellipsis">
                <div class="tc-note tc-note-info">
                    <div id="map" class="map" style="height: 300px"></div>
<%--                    <script src="<c:url value="/resources/js/webpack/deliveryEditorMap.bundle.js" />"></script>--%>
                </div>
            </div>
        </div>

        <div class="tc-example">
            <div id="delivery_editor"></div>
            <script src="<c:url value="/resources/js/webpack/DeliveryEditor.bundle.js" />"></script>
        </div>

    </div>

</div>

<jsp:include page="footer.jsp" />

<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/components.min.js" />"></script>
<script src="<c:url value="/resources/js/docs.js" />"></script>

</body>

</html>
