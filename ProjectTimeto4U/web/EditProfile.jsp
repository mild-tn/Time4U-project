<%-- 
    Document   : EditProfile
    Created on : Nov 23, 2018, 11:40:31 PM
    Author     : Mild-TN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" href="include/css/style-page.css"/> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css"/>
        <link rel="icon" type="image/png" sizes="64x64" href="images/oie_transparent.png"/>
    </head>
    <style>
        #row{
          margin-top: 50px;
          margin-bottom: 60px;
        }
    </style>
    <body>
        <jsp:include page="include/NavBarBackColor.jsp"/>
        <div class="container">
            <div class="row m-y-4" id="row">
                <!-- edit form column -->
                <div class="col-lg-12">
                    <h2>Edit Profile</h2>
                </div>
                <div class="col-lg-4 pull-lg-8 text-xs-center justify-content-center">
                    <img src="include/img/forPage/1024px-Circle-icons-profile.png" width="200px" class="m-x-auto img-fluid img-circle" alt="avatar" />
                    <label class="custom-file">
                        <input type="file" id="file" class="custom-file-input">
                    </label>
                </div>
                <div class="col-lg-4 push-lg-4 personal-info">
                    <form role="form" action="${customer == null ? 'CreateProfile':'EditProfile'}">
                        <div class="form-group row">
                            <div class="col-lg-9">
                                <input class="form-control" type="text" name="fname" placeholder="First name" required/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-lg-9">
                                <input class="form-control" type="text" name="lname" placeholder="Last name" required/>
                            </div>
                        </div>
<!--                        <div class="form-group row">
                            <div class="col-lg-9">
                                <input class="form-control" type="email" name="email" value="${account.email}" placeholder="Email" required/>
                            </div>
                        </div>-->
                        <div class="form-group row">
                            <div class="col-lg-9">
                                <input class="form-control" type="telno" name="tel" placeholder="Tel no." required/>
                            </div>
                        </div>
                </div>
                <div class="col-lg-4 push-lg-4 personal-info">
                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label">Sex</label>
                        <div class="col-lg-4">
                            <input type="radio" name="gender" value="M" required> Male
                        </div>
                        <div class="col-lg-4">
                            <input type="radio" name="gender" value="F" required> Female
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label">Address</label>
                        <div class="col-lg-10">
                            <input class="form-control" type="text" name="address" placeholder="Street" required />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label"></label>
                        <div class="col-lg-5">
                            <input class="form-control" type="text" name="city" placeholder="City" required/>
                        </div>
                        <div class="col-lg-5">
                            <input class="form-control" type="text" name="province" placeholder="Province" required />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label"></label>
                        <div class="col-lg-5">
                            <input class="form-control" type="text" name="country" placeholder="Country" required />
                        </div>
                        <div class="col-lg-5">
                            <input class="form-control" type="text" name="postCode" placeholder="Postal Code" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-3 col-form-label form-control-label"></label>
                        <div class="col-lg-9">
                            <a href="Profile.jsp" class="btn btn-secondary">Cancel</a>
                            <input type="submit" class="btn btn-primary" value="Save Changes" />
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="include/footer.jsp"/>
    </body>
</html>
