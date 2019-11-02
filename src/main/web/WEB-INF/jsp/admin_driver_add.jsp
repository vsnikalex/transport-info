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

<body onload="SetUp()">

    <jsp:include page="header.jsp" />

    <div class="main">
        <div class="breadcrumbbar">
            <div class="container-fixed">
                <ol class="breadcrumb breadcrumb-small">
                    <li>Admin Page</li>
                    <li class="active">Drivers</li>
                </ol>
            </div>
        </div>

        <div class="container-fixed">
            <h1 class="underline" id="entity_info">Driver</h1>
            <div class="tc-example">
                <fieldset class="form-fieldset">
                    <div class="form-input-set">
                        <label for="first_name">First Name</label>
                        <input type="text" class="form-input" name="first_name" id="first_name" placeholder="John">
                    </div>
                    <div class="form-input-set">
                        <label for="last_name">Last Name</label>
                        <input type="text" class="form-input" name="last_name" id="last_name" placeholder="Doe">
                    </div>
                    <div class="form-input-set">
                        <label for="location">Location</label>
                        <select name="location" id="location" class="form-select">
                            <%-- Filled with data from REST API via JS --%>
                        </select>
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
    <script src="<c:url value="/resources/js/depotAPICoord.js" />"></script>
    <script src="<c:url value="/resources/js/driverAPI.js" />"></script>

</body>

</html>
