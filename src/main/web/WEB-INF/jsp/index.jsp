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
                    <div class="navbar-portalname">HTML Components</div>
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






                            <li class=""><a href="index.html">Einführung</a></li>







                            <li class=""><a href="basics.html">Grundlagen</a></li>








                            <li>
                                <a href="#" data-open="brandnav">Dokumentation</a>
                                <div class="brandnav brandnav-lvl-2">
                                    <div class="brandnav-lvl-2-head">
                                        <button type="button" class="cancel" data-close="brandnav" data-target="#DocsMenu">
                                            <span class="icon icon-cancel" aria-hidden="true"></span>
                                        </button>
                                    </div>
                                    <ul class="nav">

                                        <li class="col-l-3">
                                            <a href="#" data-open="brandnav">CSS</a>
                                            <div class="brandnav brandnav-lvl-3">
                                                <ul class="nav">



                                                    <li class=""><a href="grid.html">Grid</a></li>



                                                    <li class=""><a href="type.html">Typografie</a></li>



                                                    <li class="active"><a href="tables.html">Tabellen</a></li>



                                                    <li class=""><a href="forms.html">Formulare</a></li>



                                                    <li class=""><a href="buttons.html">Buttons</a></li>



                                                    <li class=""><a href="images.html">Bilder</a></li>



                                                    <li class=""><a href="helper.html">Helfer</a></li>



                                                    <li class=""><a href="scss.html">SCSS</a></li>

                                                </ul>
                                            </div>
                                        </li>

                                        <li class="col-l-3">
                                            <a href="#" data-open="brandnav">Komponenten</a>
                                            <div class="brandnav brandnav-lvl-3">
                                                <ul class="nav">



                                                    <li class=""><a href="icons.html">Icons</a></li>



                                                    <li class=""><a href="badges.html">Badges</a></li>



                                                    <li class=""><a href="notifications.html">Benachrichtigungen</a></li>



                                                    <li class=""><a href="content-list.html">Content-Listen</a></li>



                                                    <li class=""><a href="pager.html">Pager</a></li>



                                                    <li class=""><a href="pagination.html">Pagination</a></li>



                                                    <li class=""><a href="breadcrumb.html">Breadcrumb</a></li>



                                                    <li class=""><a href="accessibility-seo.html">SEO und Accessibility</a></li>

                                                </ul>
                                            </div>
                                        </li>

                                        <li class="col-l-3">
                                            <a href="#" data-open="brandnav">JavaScript</a>
                                            <div class="brandnav brandnav-lvl-3">
                                                <ul class="nav">



                                                    <li class=""><a href="jsbutton.html">Button</a></li>



                                                    <li class=""><a href="expandable.html">Expandable</a></li>



                                                    <li class=""><a href="totop.html">ToTop</a></li>



                                                    <li class=""><a href="modal.html">Modal</a></li>



                                                    <li class=""><a href="qtip.html">QTip</a></li>

                                                </ul>
                                            </div>
                                        </li>

                                        <li class="col-l-3">
                                            <a href="#" data-open="brandnav">Brand</a>
                                            <div class="brandnav brandnav-lvl-3">
                                                <ul class="nav">



                                                    <li class=""><a href="header.html">Header</a></li>



                                                    <li class=""><a href="footer.html">Footer</a></li>

                                                </ul>
                                            </div>
                                        </li>

                                    </ul>
                                </div>
                            </li>



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
                    <li><a href="index.html">HTML Components 2.2.4</a></li>
                    <li class="active">Tabellen</li>
                </ol>
            </div>
        </div>
    </header>

    <div class="main">
        <div class="container-fixed">
            <h1 class="underline">Cargoes</h1>
            <h2 id="dekorationen">Dekorationen</h2>
            <h3 id="hover">Hover</h3>
            <p>Mit der CSS-Klasse <code>.hover</code> wird eine Interaktion mit der Maus sichtbar gemacht. Berührt man eine Zeile mit dem Mauszeiger, wird sie speziell hervorgehoben. Dieser Effekt wirkt sich nur auf Zeilen des <code>&lt;tbody&gt;</code> aus:</p>
            <div class="tc-example">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><span class="sr-only">Index Spalte</span></th>
                            <th>Description</th>
                            <th>Weight</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="cargoes">
                        <%-- IS FILLED WITH DATA FROM REST API --%>
                    </tbody>
                </table>
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
    <script src="<c:url value="/resources/js/cargoAPI.js" />"></script>

</body>

</html>
