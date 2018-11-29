<%-- 
    Document   : HomePage
    Created on : Nov 10, 2018, 7:12:55 PM
    Author     : Mild-TN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <title>หน้าหลัก</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" href="include/css/style-page.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css"/>
        <link rel="icon" type="image/png" sizes="64x64" href="images/oie_transparent.png">
    </head>
    <style>
        a:hover{
          text-decoration: none;
        }
        #img:hover{
          width: 310px;
        }
        section{
          background-image: url('include/img/forPage/SSeriesCollection2017.jpg');
          background-size: cover;
          background-attachment: fixed;
        }
        #box{
          width: 100vw;
          height: 20vh;
          background-color: #ffffff;
          padding: 50px;
        }

        #box1{
          width: 100vw;
          height: 100vh;
          background-color: #ffffff;
          padding: 50px;
        }
    </style>
    <body>
        <jsp:include page="include/NavBar.jsp" />
        <header>
            <a href="ShowProductServlet"><button type="button" class="btn btn-outline-light btn1">เลือกซื้อเลย >></button></a>
            <p class="p1">เท่แบบมีสไตล์ ไม่เหมือนใคร</p>
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="include/img/forPage/slide.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="include/img/forPage/slide22.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="include/img/forPage/slide3.jpg" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </header>
        <section style="height: 100vh;">
            <div id="box1" >
                <div class="container">
                    <div class="row  d-flex justify-content-center">
                        <h1 class="text-center">นาฬิกา Cassio</h1>
                        <p>นาฬิกา ผลิตจากวัสดุชั้นดี ประกอบเป็นตัวเรือนด้วยความใส่ใจในทุกรายละเอียด 
                            ส่วนประกอบทุกชิ้นได้รับการออกแบบ พัฒนาและผลิตเป็นการภายในเพื่อให้ได้มาตรฐานสูงสุด
                            มีนาฬิกาให้เลือกพิจารณาหลายรุ่นตั้งแต่นาฬิกาสำหรับมืออาชีพไปจนถึงนาฬิกาสุดคลาสสิคที่เหมาะกับข้อมือของผู้สวมใส่ทุกคน พิจารณาคอลเลคชั่นของ cassio ได้ด้วยการเลือกรุ่น วัสดุ ขอบหน้าปัด หน้าปัด และสายนาฬิกาที่คุณชื่นชอบเพื่อค้นหารุ่นที่ใช่สำหรับตัวคุณ</p>
                        <div class="d-flex bd-highlight">
                            <div class="p-2 flex-fill bd-highlight">
                                <img id="img" src="include/img/forPage/homepage_ertas_ed_viesturs_0001_670x800.jpg" width="300px"/>
                                <p>นาฬิกาทุกเรือนมีเรื่องเล่าขาน</p>
                            </div>
                            <div class="p-2 flex-fill bd-highlight">
                                <img id="img" src="include/img/forPage/homepage_about_rolex_watches_materials_0001_670x800.jpg" width="300px"/>
                                <p>วัสดุทำนาฬิกา</p>
                            </div>
                            <div class="p-2 flex-fill bd-highlight">
                                <img id="img" src="include/img/forPage/homepage_rolex_and_sports_golf_0001_670x800.jpg" width="300px"/>
                                <p>นาฬิกาทุกกับสนามก๊อฟ</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section style="height: 150vh; background-image: url('include/img/forPage/kapook_world-737808.jpg');">
            <div id="box" >
                <div class="container">
                    <div class="row  d-flex justify-content-center">
                        <h1 class="text-center">สไตล์ที่ใช่</h1>
                        <p>เป็นเวลานานกว่าศตวรรษที่นาฬิกา cassio ร่วมเดินทางไปกับคณะนักสำรวจและผู้ประสบความสำเร็จทั่วโลก นับตั้งแต่จุดสูงสุดของเทือกเขาที่สูงที่สุดไปจนถึงบริเวณที่ลึกที่สุดของมหาสมุทร ปัจจุบัน cassio ได้ปรากฏร่วมอยู่ในกิจกรรมอันทรงเกียรติสูงสุดของการแข่งขันกอล์ฟ การแล่นเรือใบ เทนนิส การแข่งรถ และทัวร์นาเมนต์ขี่ม้า 
                            Rolex ได้อุทิศตนเพื่อส่งเสริมวัฒนธรรมทั่วโลก วิทยาศาสตร์ รวมถึงการสำรวจให้มีเอกลักษณ์และยั่งยืน</p>
                    </div>
                </div>
            </div>
            <div id="box"></div>
        </section>
        <jsp:include page="include/footer.jsp"/>
    </body>
</html>
