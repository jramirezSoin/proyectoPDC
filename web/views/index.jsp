<html>
<head>
	<title>Oracle PDC</title>
	<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Oracle PDC System</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    	<ul class="nav navbar-nav">
    		<li class="dropdown">
          		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Config<span class="caret"></span></a>
          		<ul class="dropdown-menu">
            		<li><a onclick="hacerlist('/zoneModels')">Zone Models</a></li>
            		<li><a onclick="hacerlist('/impactCategories')">Impact Categories</a></li>
                        <li><a onclick="hacerlist('/rumConfig')">RUMs</a></li>
                        <li><a onclick="hacerlist('/balanceElement')">Balance Element</a></li>
          		</ul>
        	</li>
          <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Pricing<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a onclick="hacerlist('/triggerSpec')">Trigger Spec</a></li>
                <li><a onclick="hacerlist('/rollover')">Rollover</a></li>
                <li><a onclick="hacerlist('/timeModel')">Time Model</a></li>
              </ul>
          </li>
    	</ul>
        <ul class="nav navbar-nav navbar-right">
              <li class="nav-item">
        <a class="nav-link disabled" tabindex="-1" aria-disabled="true" id="InfoMessage"></a>
      </li>
      <li class="nav-item"><a class="navbar-brand"><i class="glyphicon glyphicon-chevron-down" id="hamburguer" onclick="$('#Lista').slideToggle(); $('#Principal').toggleClass('col-md-12 col-md-8'); $('#hamburguer').toggleClass('glyphicon-chevron-down glyphicon-chevron-up');"></i></a>
      </li>    
      <li class="nav-item"><a class="navbar-brand" href="/">
                <img src="/images/logo.png" width="30" height="30" alt="">
        </a></li>
        </ul>

    </div><!-- /.navbar-collapse -->
    
  </div><!-- /.container-fluid -->
</nav>
<div class="container">
  <div class="row">
    <div class="row">
  		<div class="col-xs-12 col-md-8" id="Principal"></div>
                <div class="col-xs-6 col-md-4" id="Lista" style="position: fixed; right: 0;"></div>
	</div>
  </div>
</div>

        <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
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
  <div class="modal-dialog" role="document">
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
  <div class="modal-dialog" role="document">
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/script.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>