<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>PDC System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/css/themify-icons.css">
    <link rel="stylesheet" href="/assets/css/metisMenu.css">
    <link rel="stylesheet" href="/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/assets/css/slicknav.min.css">
    <!-- amchart css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- others css -->
    <link rel="stylesheet" href="/assets/css/typography.css">
    <link rel="stylesheet" href="/assets/css/default-css.css">
    <link rel="stylesheet" href="/assets/css/styles.css">
    <link rel="stylesheet" href="/assets/css/responsive.css">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
    <!-- modernizr css -->
    <script src="/assets/js/vendor/modernizr-2.8.3.min.js"></script>
</head>

<body>
    <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
    <!-- preloader area start -->
    <div id="preloader">
        <div class="loader"></div>
    </div>
    <!-- preloader area end -->
    <!-- page container area start -->
    <div class="page-container">
        <!-- sidebar menu area start -->
        <div class="sidebar-menu">
            <div class="sidebar-header">
                <div onclick="window.location.href='/'" class="logo">
                    <h2 class="main-title">PDC System</h2>
                </div>
            </div>
            <div class="main-menu">
                <div class="menu-inner">
                    <nav>
                        <ul class="metismenu" id="menu">
                            <li><a href="#" onclick="cargaMain()"><i class="ti-home"></i><span>Home</span></a></li>
                            <li>
                                <a href="javascript:void(0)" aria-expanded="true"><i class="ti-panel"></i><span>Pricing</span></a>
                                <ul class="collapse">
                                    <li><a href="#" onclick="hacerlist('/triggerSpec', 'Trigger')">Trigger</a></li>
                                    <li><a href="#" onclick="hacerlist('/rollover', 'Rollover')">Rollover</a></li>
                                    <li><a href="#" onclick="hacerlist('/timeModel','Time Model')">Time Model</a></li>
                                    <li><a href="#" onclick="hacerlist('/bundled', 'Bundle')">Bundle Offering</a></li>
                                    <li><a href="#" onclick="hacerlist('/package', 'Package')">Package</a></li>
                                    <li>
                                        <a href="javascript:void(0)" aria-expanded="true"><i class="ti-shopping-cart"></i><span>Charge</span></a>
                                        <ul class="collapse">
                                            <li><a href="#" onclick="hacerlist('/charge', 'Charge Offering')">Charge Offering</a></li>
                                            <li><a href="#" onclick="hacerlist('/chargeRate', 'Charge Rate Plan')">Charge Rate Plan</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0)" aria-expanded="true"><i class="ti-stats-down"></i><span>Discount</span></a>
                                        <ul class="collapse">
                                            <li><a href="#" onclick="hacerlist('/alterationOffering', 'Alteration Offering')">Alteration Offering</a></li>
                                            <li><a href="#" onclick="hacerlist('/alterationRatePlan', 'Alteration Rate Plan')">Alteration Rate Plan</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0)" aria-expanded="true"><i class="ti-ruler"></i><span>Selector</span></a>
                                        <ul class="collapse">
                                            <li><a href="#" onclick="hacerlist('/chargeSelectorSpec', 'Charge Selector Spec')">Charge Selector</a></li>
                                            <li><a href="#" onclick="hacerlist('/genericSelector', 'Generic Selector')">Generic Selector</a></li>
                                        </ul>
                                    </li>    
                                </ul>
                            </li>    
                            <li>
                                <a href="javascript:void(0)" aria-expanded="true"><i class="ti-settings"></i><span>Config
                                    </span></a>
                                <ul class="collapse">
                                    <li><a href="#" onclick="hacerlist('/zoneModels', 'Zone Model')">Zone Model</a></li>
                                    <li><a href="#" onclick="hacerlist('/impactCategories', 'Impact Category')">Impact Category</a></li>
                                    <li><a href="#" onclick="hacerlist('/rumConfig','RUM')">RUM</a></li>
                                    <li><a href="#" onclick="hacerlist('/balanceElement', 'Balance Element')">Balance Element</a></li>
                                    <li><a href="#" onclick="hacerlist('/holiday', 'Holiday Calendar')">Holiday Calendar</a></li>
                                </ul>
                            </li>
                            
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <!-- sidebar menu area end -->
        <!-- main content area start -->
        <div class="main-content">
            <!-- header area start -->
            <div class="header-area">
                <div class="row align-items-center">
                    <!-- nav and search button -->
                    <div class="col-md-6 col-sm-8 clearfix">
                        <div class="nav-btn pull-left">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                        
                    </div>
                    <!-- profile info & task notification -->
                    <div class="col-md-6 col-sm-4 clearfix">
                        <ul class="notification-area pull-right">
                            <li id="full-view"><i class="ti-fullscreen"></i></li>
                            <li id="full-view-exit"><i class="ti-zoom-out"></i></li>
                            <li class="settings-btn">
                                <i class="ti-view-list-alt"></i>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- header area end -->
            <!-- page title area start style="position: fixed; height: 90vh; overflow-y: auto;"-->
            <button onclick="topFunction()" class="btn btn-sm btn-primary bg5" id="myGoUpBtn" title="Go to top">
                <i class="ti-angle-up"></i>
            </button>
            <div id="Principal" ></div>
        </div>
        <!-- main content area end -->
        <!-- footer area start-->
        <!--footer>
            <div class="footer-area">
                
            </div>
        </footer-->
        <!-- footer area end-->
    </div>
    <!-- page container area end -->
    <!-- offset area start -->
    <div class="offset-area">
        <div class="offset-close"><i class="ti-close"></i></div>
                <ul class="nav offset-menu-tab">
            <li><a class="active" data-toggle="tab" href="#activity">List</a></li>
        </ul>
        <div class="offset-content tab-content">    
            <div id="activity" class="tab-pane fade in show active">
                <div id="Lista" class="recent-activity">
                </div>
            </div>
        </div>
    </div>

            <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="exampleModalLabel"></h2>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        <div class="modal-body" id="ModalBody">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="update();">Save changes</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="eliminarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <h2 class="modal-title"></h2>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        <div class="modal-body">
            Are you sure you want to delete this?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="remove();">Delete</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="addModalLabel"></h2>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        <div class="modal-body" id="addModalBody">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="add();">Add</button>
      </div>
    </div>
  </div>
</div>
            
<div class="modal fade" id="pdfModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="pdfModalTitle"></h2>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        <form action="/downloadPdf" method="POST">
      <div class="modal-body" id="pdfModalBody">
        <b class="text-muted mb-3 d-block">Reporte:</b>
        <div class="custom-control custom-radio">
            <input type="radio" checked id="customRadio1" name="customRadio" value="General" class="custom-control-input">
            <label class="custom-control-label" for="customRadio1">General</label>
        </div>
        <div class="custom-control custom-radio">
            <input type="radio" id="customRadio2" name="customRadio" value="Detallado" class="custom-control-input">
            <label class="custom-control-label" for="customRadio2">Detallado</label>
        </div>
        <b class="text-muted mb-3 d-block">Nodos:</b>
        <div class="custom-control custom-radio">
            <input type="radio" checked id="customRadio3" name="customRadioD" value="Todos" class="custom-control-input">
            <label class="custom-control-label" for="customRadio3">Todos</label>
        </div>
        <div class="custom-control custom-radio">
            <input type="radio" id="customRadio4" name="customRadioD" value="Seleccionados" class="custom-control-input">
            <label class="custom-control-label" for="customRadio4">Seleccionados</label>
        </div>
        <input type="hidden" value="" name="pdfChecks" id="pdfChecks">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <input  type="submit" class="btn btn-primary" value="Download PDF">
      </div>
        </form>      
    </div>
  </div>
</div>

<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModal" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="errorModalLabel"></h2>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        <div class="modal-body" id="errorModalBody">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>            


    <!-- offset area end -->
    <!-- jquery latest version -->
    <script src="/assets/js/vendor/jquery-2.2.4.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
    <!-- bootstrap 4 js -->
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/owl.carousel.min.js"></script>
    <script src="/assets/js/metisMenu.min.js"></script>
    <script src="/assets/js/jquery.slimscroll.min.js"></script>
    <script src="/assets/js/jquery.slicknav.min.js"></script>

    <!-- start chart js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
    <!-- start highcharts js -->
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <!-- start zingchart js -->
    <script src="https://cdn.zingchart.com/zingchart.min.js"></script>
    <script>
    zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
    ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"];
    </script>
    <!-- all line chart activation -->
    <script src="/assets/js/line-chart.js"></script>
    <!-- all pie chart -->
    <script src="/assets/js/pie-chart.js"></script>
    <!-- others plugins -->
    <script src="/assets/js/plugins.js"></script>
    <script src="/assets/js/scripts.js"></script>
    <script src="/assets/js/script.js"></script>
    <script> cargaMain(); </script>
</body>

</html>
