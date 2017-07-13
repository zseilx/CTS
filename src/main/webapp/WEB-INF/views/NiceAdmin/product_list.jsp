<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="resources/img/favicon.png">

    <title>3MS</title>

    <!-- Bootstrap CSS -->    
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="resources/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="resources/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="resources/css/font-awesome.min.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/style-responsive.css" rel="stylesheet" />
  </head>

  <body>
   <!-- container section start -->
  <section id="container" class="">
      <!--header start-->
      <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i class="icon_menu"></i></div>
            </div>

            <!--logo start-->
            <a href="index" class="logo">Mart <span class="lite">Marketing Management</span></a>
            <!--logo end-->

            <div class="top-nav notification-row">       
                     
                <!-- notificatoin dropdown start-->
                <ul class="nav pull-right top-menu">
                
                    <!-- alert notification start-->
                    <li id="alert_notificatoin_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">

                            <i class="icon-bell-l"></i>
                            <span class="badge bg-important">7</span>
                        </a>
                        <ul class="dropdown-menu extended notification">
                            <li>
                                <p class="blue">알림 갯수</p>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-primary"><i class="icon_profile"></i></span> 
                                    Friend Request
                                    <span class="small italic pull-right">5 mins</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-warning"><i class="icon_pin"></i></span>  
                                    John location.
                                    <span class="small italic pull-right">50 mins</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-danger"><i class="icon_book_alt"></i></span> 
                                    Project 3 Completed.
                                    <span class="small italic pull-right">1 hr</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-success"><i class="icon_like"></i></span> 
                                    Mick appreciated your work.
                                    <span class="small italic pull-right"> Today</span>
                                </a>
                            </li>                            
                            <li>
                                <a href="#">See all notifications</a>
                            </li>
                        </ul>
                    </li>
                    <!-- alert notification end-->
                    
                    <!-- user login dropdown start-->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img alt="" src="resources/img/avatar1_small.jpg">
                            </span>
                            <span class="username">Jenifer Smith</span>
                            <b class="caret"></b>
                        </a>
                        
                        <ul class="dropdown-menu extended logout">
                            <li class="eborder-top">
                                <a href="#"><i class="icon_profile"></i> My Profile</a>
                            </li>
                            <li>
                                <a href="login"><i class="icon_key_alt"></i> Log Out</a>
                            </li>
                        </ul>
                    </li>
                    <!-- user login dropdown end -->
                    
                </ul>
                <!-- notificatoin dropdown end-->
            </div>
      </header>      
      <!--header end-->

      	<!--sidebar start-->
      	<aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu">                
                  <li class="active">
                      <a class="" href="index">
                          <i class="icon_house_alt"></i>
                          <span>Dashboard</span>
                      </a>
                  </li>
				  <li class="sub-menu">
                      <a href="#" class="">
                          <i class="icon_document_alt"></i>
                          <span>Management</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="register_shop">매장 등록</a></li>                          
                          <li><a class="" href="product_list">물품 등록</a></li>
                          <li><a class="" href="#">매출 관리</a></li>
                          <li><a class="" href="#">재고 관리</a></li>
                      </ul>
                  </li>       
                  <li class="sub-menu">
                      <a href="event" class="">
                          <i class="icon_desktop"></i>
                          <span>Event</span>
                      </a>
                  </li>
                  <li>
                      <a class="" href="coupon">
                          <i class="icon_genius"></i>
                          <span>Coupon</span>
                      </a>
                  </li>
                  <li>                     
                      <a class="" href="chart-chartjs">
                          <i class="icon_piechart"></i>
                          <span>POS</span>
                          
                      </a>
                                         
                  </li>
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa fa-bars"></i> 매장 등록</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="index">Home</a></li>
						<li><i class="fa fa-bars"></i>Management</li>
					</ol>
				</div>
			</div>
			
            <!-- page start-->
             	<div class="row">
                  <div class="col-lg-12">
                  	<div class="panel panel-default">
							<div class="panel-heading">
								<h2><i class="fa fa-map-marker red"></i><strong>ProductList</strong></h2>
								<div class="panel-actions">
									<a href="register_product" class="btn-setting"><i class="fa fa-plus" aria-hidden="true"></i></a>
								</div>
							</div>
		                    <section class="panel" style="overflow:scroll; height:430px;">
		                          
		                          <table class="table table-striped table-advance table-hover">
		                           <tbody>
		                              <tr>
		                                 <th style="text-align:center;"><i class="icon_profile"></i> Product_no</th>
		                                 <th style="text-align:center;"><i class="icon_calendar"></i> Category_no</th>
		                                 <th style="text-align:center;"><i class="icon_mail_alt"></i> Category_name</th>
		                                 <th style="text-align:center;"><i class="icon_pin_alt"></i> Product_name</th>
		                                 <th style="text-align:center;"><i class="icon_pin_alt"></i> Product_Amount</th>
		                                 <th style="text-align:center;"><i class="icon_pin_alt"></i> Price(won)</th>
		                                 <th style="text-align:center;"></th>
		                              </tr>
		                              
		                              <tr>
		                                 <td style="text-align:center;">001</td>
		                                 <td style="text-align:center;">03</td>
		                                 <td style="text-align:center;">가전 제품</td>
										 <td style="text-align:center;">** 고데기</td>
										 <td style="text-align:center;">15</td>
										 <td style="text-align:center;">25000</td>
		                                 <td>
		                                  <div class="btn-group">
		                                      <a class="btn btn-primary" href="product_info"><i class="icon_plus_alt2"></i></a>
		                                      <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
		                                      <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
		                                  </div>
		                                 </td>
		                              </tr>
		                              
		                              <tr>
		                                 <td style="text-align:center;">002</td>
		                                 <td style="text-align:center;">03</td>
		                                 <td style="text-align:center;">가전 제품</td>
										 <td style="text-align:center;">** 고데기</td>
										 <td style="text-align:center;">15</td>
										 <td style="text-align:center;">25000</td>
		                                 <td>
		                                  <div class="btn-group">
		                                      <a class="btn btn-primary" href="product_info"><i class="icon_plus_alt2"></i></a>
		                                      <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
		                                      <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
		                                  </div>
		                                 </td>
		                              </tr>
		                              
		                              <tr>
		                                 <td style="text-align:center;">003</td>
		                                 <td style="text-align:center;">03</td>
		                                 <td style="text-align:center;">가전 제품</td>
										 <td style="text-align:center;">** 고데기</td>
										 <td style="text-align:center;">15</td>
										 <td style="text-align:center;">25000</td>
		                                 <td>
		                                  <div class="btn-group">
		                                      <a class="btn btn-primary" href="product_info"><i class="icon_plus_alt2"></i></a>
		                                      <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
		                                      <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
		                                  </div>
		                                 </td>
		                              </tr>
		                              
		                              <tr>
		                                 <td style="text-align:center;">004</td>
		                                 <td style="text-align:center;">03</td>
		                                 <td style="text-align:center;">가전 제품</td>
										 <td style="text-align:center;">** 고데기</td>
										 <td style="text-align:center;">15</td>
										 <td style="text-align:center;">25000</td>
		                                 <td>
		                                  <div class="btn-group">
		                                      <a class="btn btn-primary" href="product_info"><i class="icon_plus_alt2"></i></a>
		                                      <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
		                                      <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
		                                  </div>
		                                 </td>
		                              </tr>
		                              
		                              <tr>
		                                 <td style="text-align:center;">005</td>
		                                 <td style="text-align:center;">03</td>
		                                 <td style="text-align:center;">가전 제품</td>
										 <td style="text-align:center;">** 고데기</td>
										 <td style="text-align:center;">15</td>
										 <td style="text-align:center;">25000</td>
		                                 <td>
		                                  <div class="btn-group">
		                                      <a class="btn btn-primary" href="product_info"><i class="icon_plus_alt2"></i></a>
		                                      <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
		                                      <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
		                                  </div>
		                                 </td>
		                              </tr>
		                                                    
		                           </tbody>
		                        </table>
		                      </section>
                      </div>
                  </div>
              </div>
           <!-- page end-->
              
          </section>
      </section>
  </section>
  <!-- container section end -->
    <!-- javascripts -->
    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <!-- nice scroll -->
    <script src="resources/js/jquery.scrollTo.min.js"></script>
    <script src="resources/js/jquery.nicescroll.js" type="text/javascript"></script><!--custome script for all page-->
    <script src="resources/js/scripts.js"></script>


  </body>
</html>
