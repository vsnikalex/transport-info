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

<body onload="RestGet()">

    <header class="brand-header" id="DocsHeader">
        <div class="brandbar">
            <div class="container-fixed">
                <div class="brand-logo">
                    <img src="<c:url value="/resources/images/deutsche-telekom-logo.svg" />" alt="Telekom Logo">
                    <span class="sr-only">Telekom Logo</span>
                </div>
                <div class="brand-claim">
                    <img src="<c:url value="/resources/images/brand-claim.svg" />" alt="Brand Claim">
                    <span class="sr-only">Brand Claim</span>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-default">
            <div class="container-fixed">
                <div class="navbar-expanded">
                    <div class="navbar-portalname">Admin Page</div>
                    <div class="brandnavhead" data-spy="brandnav" data-target="#DocsMenu">
                        <button type="button" class="btn btn-clean navbar-btn brandnav-control-up" data-close="brandnav" data-target="#DocsMenu">
                            <span class="sr-only">Back</span>
                            <span class="icon icon-navigation-left"></span>
                        </button>
                        <button type="button" class="btn btn-clean navbar-btn navbar-right" data-cancel="brandnav" data-target="#DocsMenu">
                            <span class="sr-only">Close Navigation</span>
                            <span class="icon icon-cancel"></span>
                        </button>
                        <label class="navbar-header-label brandnav-label">Home</label>
                    </div>
                    <div class="brandnav brandnav-lvl-1" id="DocsMenu">
                        <ul class="nav">
                            <li class=""><a href="/admin_truck">Trucks</a></li>
                            <li class=""><a href="/admin_driver">Drivers</a></li>
                            <li class=""><a href="/admin_cargo">Cargoes</a></li>
                            <li class=""><a href="/admin_order">Orders</a></li>
                        </ul>
                    </div>

                </div>
                <div class="navbar-persistent">
                    <button type="button" class="btn btn-clean navbar-btn navbar-toggle" data-open="brandnav" data-target="#DocsMenu">
                        <span class="sr-only">Open Navigation</span>
                        <span class="icon icon-list-view"></span>
                    </button>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <button type="button" class="btn btn-default docs-theme-toggle-btn" data-toggle="theme">
                                <span class="visible-on-bright">Dark Theme</span>
                                <span class="visible-on-dark">Bright Theme</span>
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="breadcrumbbar">
            <div class="container-fixed">
                <ol class="breadcrumb breadcrumb-small">
                    <li><a href="index.html">Admin Page</a></li>
                    <li class="active">Trucks</li>
                </ol>
            </div>
        </div>
    </header>

    <div class="main">


        <div class="container-fixed">
            <h1 class="underline">Truck</h1>
            <div class="tc-example">
                <fieldset class="form-fieldset">
                    <div class="form-input-set">
                        <label for="plate">Plate</label>
                        <input type="text" class="form-input" name="plate" id="plate" placeholder="AB12345">
                    </div>
                    <div class="form-input-set">
                        <label for="capacity">Capacity</label>
                        <input type="number" class="form-input" name="capacity" id="capacity" placeholder="0">
                    </div>
                    <div class="form-input-set">
                        <label for="status">Status</label>
                        <select name="status" id="status" class="form-select">
                            <option value="OK" selected>OK</option>
                            <option value="DEFECTIVE">DEFECTIVE</option>
                        </select>
                    </div>
                    <div class="form-input-set">
                        <label for="location">Location</label>
                        <select name="location" id="location" class="form-select">
                            <option value="48.7525249,18.1450552" selected>SLOVAKIA</option>
                            <option value="44.0639782,40.4920936">KAVKAZ</option>
                        </select>
                    </div>
                    <button type="button" class="btn btn-default btn-block" onclick="RestPost()">Save</button>
                </fieldset>
            </div>
        </div>


    </div>

    <footer class="brand-footer">
        <div class="container-fixed">
            <div class="row brand-footer-bar hidden-xl hidden-l hidden-m">
                <div class="col-l-3 col-m-2 col-s-12">
                    <div class="brand-logo">
                        <a href="#">
                            <img src="<c:url value="/resources/images/deutsche-telekom-logo.svg" />" alt="Telekom Logo">
                            <span class="sr-only">Telekom Logo</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="brand-footer-separator hidden-xl hidden-l hidden-m"></div>
        <div class="container-fixed">
            <div class="row brand-footer-bar">
                <div class="col-l-3 col-m-2 hidden-s hidden-xs">
                    <div class="brand-logo">
                        <a href="#">
                            <img src="<c:url value="/resources/images/deutsche-telekom-logo.svg" />" alt="Telekom Logo">
                            <span class="sr-only">Telekom Logo</span>
                        </a>
                    </div>
                </div>
                <div class="col-l-9 col-m-12 text-l-right text-m-center text-muted">
                    <div class="brand-footer-bar-text">&copy; 2015 Deutsche Telekom AG | Product Design | Creative Direction</div>
                </div>
            </div>
        </div>
    </footer>

    <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/components.min.js" />"></script>
    <script src="<c:url value="/resources/js/docs.js" />"></script>

    <%-- USE REST API --%>
    <script src="<c:url value="/resources/js/truckAPI.js" />"></script>

</body>

</html>
