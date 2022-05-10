<%@ page import="java.util.List" %>
<%@ page import="com.example.barbershop.model.MenuItem" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script&display=swap" rel="stylesheet">
    <title>Barbershop</title>

    <style>
        html, body, .container {
            height: 100%;
        }
        html, body, input, textarea {
            font-family: 'Ubuntu', sans-serif;
        }
        body {
            padding: 0;
            margin: 0;
            background: url("https://images.unsplash.com/photo-1592647420148-bfcc177e2117") no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }

        #logo-text {
            font-size: 36px;
            font-family: 'Pacifico', cursive;
            color: #fff !important;
        }
        .nav-item:hover,
        .nav-item.active{
            background-color: rgba(255, 255, 255, 0.25) !important;
        }

        .navbar-toggler {
            color: #fafafa !important;
        }

        a,
        a:active,
        a:visited {
            color: #fafafa !important;
        }

        a:hover {
            color: #e0e0e0 !important;
        }

        .nav-link,
        .nav-link:active,
        .nav-link:visited {
            color: #fafafa !important;
        }

        .nav-link:hover {
            color: #e0e0e0 !important;
        }
        #main-title {
            position: absolute;
            left: 50%;
            top: calc(50% - 50px);
            text-align: center;
            width: 100%;
            -moz-transform: translate(-50%, -50%); /* Firefox */
            -ms-transform: translate(-50%, -50%);  /* IE 9 */
            -webkit-transform: translate(-50%, -50%); /* Safari and Chrome*/
            -o-transform: translate(-50%, -50%); /* Opera */
            transform: translate(-50%, -50%);

            padding: 50px;
            font-size: max(36px, 3.2vw);
            font-family: 'Dancing Script', cursive;
            color: #fafafa !important;
        }

        #reservations {
            position: fixed;
            bottom: 0;
            right: 0;
            background-color: rgba(163, 0, 0, 0.7);
            width: min(400px, 100%);
            text-align: center;
            padding: 80px 0;
        }

        #reservations a,
        #reservations a:hover,
        #reservations a:active,
        #reservations a:visited {
            text-align: center;
            font-size: 24px;
            color: #fafafa;
            text-decoration: none;
            text-transform: capitalize;
        }
    </style>
</head>
<body>

<div class="container">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" id="logo-text" href="${pageContext.request.contextPath}/">Barbershop</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <%
                        List<MenuItem> menu = (List) request.getAttribute("menuList");
                        for (MenuItem mItem: menu) {
                    %>
                    <li class="nav-item <%= mItem.getActive() ? "active" : "" %>">
                        <a class="nav-link" href="${pageContext.request.contextPath}/<%= mItem.getUrl() %>"><%= mItem.getName() %></a>
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Title -->
    <p id="main-title">
        Being a barber is about taking care of the people!
    </p>

    <!-- Reservations button -->
    <p id="reservations">
        <a href="${pageContext.request.contextPath}/reservations"><i class="fa-solid fa-calendar-days"></i> &nbsp;Make a reservation </a>
    </p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>