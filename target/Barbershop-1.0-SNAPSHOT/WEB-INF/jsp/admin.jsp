<%@ page import="com.barbershop.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <title>Barbershop Admin area</title>

    <style>
        html, body {
            padding: 0;
            margin: 0;
            height: 100%;
            width: 100%;
        }

        .header-nav {
            width: 100%;
            background-color: #558b2f;
            padding: 15px 10px 15px 0;
            float: left;
            clear: both;
            color: #ECF0F5;
        }

        .content {
            width: 100%;
            height: calc(100% - 50px);
            background-color: #ECF0F5;
            padding: 20px;
            float: left;
            clear: both;
        }

        .content h1 {
            font-size: 24px;
            font-weight: unset;
            color: #333333;
            padding-bottom: 10px;
        }
        .user-image {
            float: left;
            width: 25px;
            height: 25px;
            border-radius: 50%;
            margin-right: 10px;
            margin-top: -2px;
        }
        .table-padding {
            padding: 20px;
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>

<div class="header-nav">
    <div class="container">
        <div style="text-transform: uppercase; float: left;">
            <a style="color: white;" href="${pageContext.request.contextPath}/">Barbershop Admin area</a>
        </div>
        <div style="float: right;">
            <img src="https://adminlte.io/themes/AdminLTE/dist/img/user2-160x160.jpg" class="user-image" /> Admin User
        </div>
    </div>
</div>

<div class="content">
    <div class="container">
        <h1>Order list</h1>
        <div class="table-padding table-light">
            <table class="table table-hover table-light">
                <thead>
                <form action="${pageContext.request.contextPath}/admin" method="post">
                    <tr>
                        <td>
                            <span style="float: left; padding-right: 10px;" >ID</span> <input type="text" value="<%=request.getAttribute("formId")%>" name="id" class="form-control form-control-sm" style="float: left; width: 50px;">
                        </td>
                        <td>
                            <span style="float: left; padding-right: 10px;" >Client</span> <input type="text" value="<%=request.getAttribute("formClient")%>" name="client" class="form-control form-control-sm" style="float: left; width: 150px;">
                        </td>
                        <td>
                            <span style="float: left; padding-right: 10px;" >Service</span> <input type="text" value="<%=request.getAttribute("formService")%>" name="service" class="form-control form-control-sm" style="float: left; width: 100px;">
                        </td>
                        <td>
                            <span style="float: left; padding-right: 10px;" >Price</span> <input type="text" value="<%=request.getAttribute("formPrice")%>" name="price" class="form-control form-control-sm" style="float: left; width: 50px;">
                        </td>
                        <td>
                            <span style="float: left; padding-right: 10px;" >Date</span> <input type="text" value="<%=((Timestamp)request.getAttribute("formDate")).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))%>" placeholder="dd.mm.yyyy" name="date" class="form-control form-control-sm" style="float: left; width: 100px;">
                        </td>
                        <td>
                            <button style="float: right;" type="submit" class="btn btn-secondary btn-sm"><i class="fa-solid fa-magnifying-glass"></i></button>
                        </td>
                    </tr>
                </form>
                </thead>
                <tbody>
                <%
                    for (Order o: (List<Order>) request.getAttribute("calendarEvents")) {
                        int orderDate = Integer.parseInt(o.getTimestamp().toLocalDateTime().format(DateTimeFormatter.ofPattern("YYYYMMdd")));
                        int today = Integer.parseInt(new Timestamp(System.currentTimeMillis()).toLocalDateTime().format(DateTimeFormatter.ofPattern("YYYYMMdd")));
                %>
                <tr <% if (orderDate == today) { %> class="" <% } %>>
                    <td
                    <%

                    if (orderDate > today) {
                    %>
                        title="Future reservation"><i class="fa-solid fa-angles-up text-success"></i>
                    <%
                    } else if (orderDate < today) {
                    %>
                        title="Past reservation"><i class="fa-solid fa-angles-down text-danger"></i>
                    <%
                    } else {
                    %>
                        title="Today reservation"><i class="fa-solid fa-angles-right"></i>
                    <%
                    }
                    %> <%=o.getId()%></td>
                    <td><%= o.getClient().getFirstName() %> <%=o.getClient().getGender().getId() == 1 ? "<i class=\"fa-solid fa-mars\"></i>" : "<i class=\"fa-solid fa-venus\"></i>"%></td>
                    <td><%= o.getService().getName() %></td>
                    <td>€<%= String.format("%.2f", o.getService().getPrice()) %></td>
                    <td><%= o.getTimestamp().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")) %></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/admin" method="post">
                            <input type="hidden" name="delete" value="<%=o.getId()%>" />
                            <button style="float: right;" type="submit" class="btn btn-danger btn-sm"><i class="fa-solid fa-trash"></i></button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
