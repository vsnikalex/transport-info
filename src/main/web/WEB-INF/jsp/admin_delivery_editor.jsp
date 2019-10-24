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

<body onload="SetUp()">

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

        <div class="row">

            <div class="col-l-3 ">
                <div class="form-input-set">
                    <label for="selectbox" title="Primäroptionen Auswahlliste">Depot</label>
                    <select name="select" id="selectbox" class="form-select">
                        <option value="opt1" selected>Slovakia (CORP)</option>
                        <option value="opt2">Russia (CLIENT)</option>
                    </select>
                </div>
            </div>

            <div class="col-l-3 ">
                <ul class="content-list">
                    <li class="media">
                        <div class="media-body">
                            <div class="form-checkbox-set">
                                <label>
                                    <input type="checkbox" name="cb0" value="0" class="form-checkbox">Tesla Model X
                                </label>
                            </div>
                            <div class="media-hint">2700kg Russia(CLIENT) PREPARED</div>
                        </div>
                    </li>
                    <li class="media">
                        <div class="media-body">
                            <div class="form-checkbox-set">
                                <label>
                                    <input type="checkbox" name="cb0" value="0" class="form-checkbox">IKEA Sofa
                                </label>
                            </div>
                            <div class="media-hint">100kg Russia(CLIENT) PREPARED</div>
                        </div>
                    </li>
                    <li class="media">
                        <div class="media-body">
                            <div class="form-checkbox-set">
                                <label>
                                    <input type="checkbox" name="cb0" value="0" class="form-checkbox">Tirion Lanister
                                </label>
                            </div>
                            <div class="media-hint">30kg Russia(CLIENT) DRUNK</div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="col-l-3 ">
                <ul class="content-list">
                    <li class="media">
                        <div class="media-body">
                            <div class="form-radio-set">
                                <label>
                                    <input type="radio" name="rb" value="rb1" class="form-radio">YG78923 Loaded: 50%
                                </label>
                            </div>
                            <div class="media-hint">10 000kg Krakow OK</div>
                        </div>
                    </li>
                    <li class="media">
                        <div class="media-body">
                            <div class="form-radio-set">
                                <label>
                                    <input type="radio" name="rb" value="rb1" class="form-radio">QF66478 Loaded: 30%
                                </label>
                            </div>
                            <div class="media-hint">5 000kg Belgrad OK</div>
                        </div>
                    </li>
                    <li class="media">
                        <div class="media-body">
                            <div class="form-radio-set">
                                <label>
                                    <input type="radio" name="rb" value="rb1" class="form-radio">NJ12345 Loaded: 30%
                                </label>
                            </div>
                            <div class="media-hint">1000kg Krasnodar OK</div>
                        </div>
                    </li>
                </ul>
            </div>
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
