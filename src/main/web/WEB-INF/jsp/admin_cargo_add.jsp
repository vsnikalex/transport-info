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

<body onload="SetUp()">

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
            <h1 class="underline" id="entity_info">Cargo</h1>
            <div class="tc-example">
                <fieldset class="form-fieldset">
                    <div class="form-input-set">
                        <label for="description">Description</label>
                        <input type="text" class="form-input" name="description" id="description" placeholder="Description">
                    </div>
                    <div class="form-input-set">
                        <label for="location">Location</label>
                        <select name="location" id="location" class="form-select">
                            <%-- Filled with data from REST API via JS --%>
                        </select>
                    </div>
                    <div class="form-input-set">
                        <label for="destination">Destination</label>
                        <select name="destination" id="destination" class="form-select">
                            <%-- Filled with data from REST API via JS --%>
                        </select>
                    </div>
                    <div class="form-input-set">
                        <label for="status">Status</label>
                        <select name="status" id="status" class="form-select">
                            <option value="PREPARED" selected>PREPARED</option>
                            <option value="SHIPPED">SHIPPED</option>
                            <option value="DELIVERED">DELIVERED</option>
                        </select>
                    </div>
                    <div class="form-input-set">
                        <label for="weight">Weight</label>
                        <input type="number" class="form-input" name="weight" id="weight" placeholder="0">
                    </div>
                    <button id="save_button" type="button" class="btn btn-default btn-block">Save</button>
                </fieldset>
            </div>
        </div>


    </div>

    <jsp:include page="footer.jsp" />

    <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/components.min.js" />"></script>
    <script src="<c:url value="/resources/js/docs.js" />"></script>

    <%-- USE REST API --%>
    <script src="<c:url value="/resources/js/depotAPIId.js" />"></script>
    <script src="<c:url value="/resources/js/cargoAPI.js" />"></script>

</body>

</html>
