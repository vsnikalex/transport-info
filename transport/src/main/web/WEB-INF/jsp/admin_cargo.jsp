<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Transport-Info</title>
    <meta name="description" content="">

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

<body onload="RestGetAll()" style="display: flex;
                                    flex-direction: column;
                                    min-height: 100%;">

    <jsp:include page="header.jsp" />

    <div class="main" style="min-height: calc(100vh - 249px);">
        <div class="breadcrumbbar">
            <div class="container-fixed">
                <ol class="breadcrumb breadcrumb-small">
                    <li>Admin Page</li>
                    <li class="active">Cargoes</li>
                </ol>
            </div>
        </div>

        <div class="container-fixed">

            <div class="row">
                <div class="demo-col col-l-12">
                    <div class="tc-note tc-note-info">
                        <div id="map" class="map" style="height: 300px"></div>
                        <script src="<c:url value="/resources/js/webpack/map.bundle.js" />"></script>
                    </div>
                </div>
            </div>

            <div class="tc-example">
                <button class="btn btn-default btn-block" onclick="location.href='/admin/cargo_add'">
                    <i class="icon icon-add" aria-hidden="true"></i>
                </button>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><span class="sr-only">Index Spalte</span></th>
                            <th>Description</th>
                            <th>Weight</th>
                            <th>Status</th>
                            <th>Location</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="data">
                        <script src="<c:url value="/resources/js/cargoAPI.js" />"></script>
                    </tbody>
                </table>
            </div>
        </div>


    </div>

    <jsp:include page="footer.jsp" />

    <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/components.min.js" />"></script>
    <script src="<c:url value="/resources/js/docs.js" />"></script>


</body>

</html>
