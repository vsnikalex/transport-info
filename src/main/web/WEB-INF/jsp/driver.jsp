<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
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

</head>

<body>

<jsp:include page="header.jsp" />

<div class="main">
    <div class="breadcrumbbar">
        <div class="container-fixed">
            <ol class="breadcrumb breadcrumb-small">
                <li>DriverPage</li>
            </ol>
        </div>
    </div>

    <div class="container-fixed">
        <div class="main">

            <div id="driver_app"></div>
            <script src="<c:url value="/resources/js/webpack/DriverApp.bundle.js" />"></script>

            <div class="container-fixed demo-grid">
                <div class="row">
                    <div class="col-l-4 ">

                        <h2 class="underline">Delivery #36</h2>
                        <div class="content-list">

                            <a class="content-list-item" data-toggle="button" data-callback="expandSingle">
                                <span class="badge badge">0</span><span class="badge badge-brand">2</span>
                                CORP PREPARED
                            </a>
                            <div id="exp-single">
                                <a class="content-list-item">
                                    <div class="row">
                                        <div class="col-l-8">
                                            CARGO #1
                                        </div>
                                        <div class="col-l-2">
                                            <button class="btn btn-default btn-icon">
                                                <i class="icon icon-upload" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                    </div>
                                </a>
                                <a class="content-list-item">
                                    <div class="row">
                                        <div class="col-l-8">
                                            CARGO #2
                                        </div>
                                        <div class="col-l-2">
                                            <button class="btn btn-default btn-icon">
                                                <i class="icon icon-upload" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                    </div>
                                </a>
                            </div>

                            <a class="content-list-item"><span class="badge badge">0</span><span class="badge badge">2</span>CORP ALL_SHIPPED</a>
                            <a class="content-list-item"><span class="badge badge-brand">1</span><span class="badge badge">0</span> CLIENT#1 SHIPPED</a>
                            <a class="content-list-item"><span class="badge badge">1</span><span class="badge badge">0</span> CLIENT#1 ALL_DELIVERED</a>
                            <a class="content-list-item"><span class="badge badge-brand">1</span><span class="badge badge">0</span> CLIENT#2 SHIPPED</a>
                            <a class="content-list-item"><span class="badge badge">1</span><span class="badge badge">0</span> CLIENT#2 ALL_DELIVERED</a>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>


</div>


<jsp:include page="footer.jsp" />

<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/components.min.js" />"></script>
<script src="<c:url value="/resources/js/docs.js" />"></script>


</body>

</html>
