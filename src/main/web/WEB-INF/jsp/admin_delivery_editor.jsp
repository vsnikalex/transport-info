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
    </div>

    <div class="container-fixed demo-grid grid-debug">

        <div id="delivery_editor"></div>
        <script src="<c:url value="/resources/js/webpack/DeliveryEditor.bundle.js" />"></script>

        <div class="row">
            <div class="col-l-3 ">
                <ul class="content-list">
                    <li class="media">
                        <div class="media-body">
                            <div class="form-checkbox-set">
                                <label>
                                    <input type="checkbox" name="cb0" value="0" class="form-checkbox">Test Testovich
                                </label>
                            </div>
                            <div class="media-hint">Krakow REST</div>
                        </div>
                    </li>
                    <li class="media">
                        <div class="media-body">
                            <div class="form-checkbox-set">
                                <label>
                                    <input type="checkbox" name="cb0" value="0" class="form-checkbox">Jorah Mormont
                                </label>
                            </div>
                            <div class="media-hint">Krakow REST</div>
                        </div>
                    </li>
                </ul>
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
