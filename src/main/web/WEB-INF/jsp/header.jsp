<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <li class=""><a href="<c:url value="/admin/truck"/>">Trucks</a></li>
                        <li class=""><a href="<c:url value="/admin/driver"/>">Drivers</a></li>
                        <li class=""><a href="<c:url value="/admin/cargo"/>">Cargoes</a></li>
                        <li class=""><a href="<c:url value="/admin/delivery_editor"/>">Deliveries</a></li>
                        <li class=""><a href="<c:url value="/admin/driver_app"/>">Driver Page</a></li>
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

</header>