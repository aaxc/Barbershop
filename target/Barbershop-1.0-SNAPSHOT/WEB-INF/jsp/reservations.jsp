<%@ page import="java.util.List" %>
<%@ page import="com.barbershop.model.Menu" %>
<%@ page import="com.barbershop.model.Service" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          crossorigin="anonymous"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" rel="stylesheet"
          crossorigin="anonymous"/>

    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.css' rel='stylesheet'/>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.min.css' rel='stylesheet'/>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/timegrid/main.min.css' rel='stylesheet'/>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/list/main.min.css' rel='stylesheet'/>

    <link rel="preconnect" href="https://fonts.googleapis.com" crossorigin="anonymous"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="anonymous"/>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300&display=swap" rel="stylesheet"
          crossorigin="anonymous"/>
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet"
          crossorigin="anonymous"/>
    <title>Barbershop</title>

    <style>
        html, body, .container {
            height: 100%;
        }

        html, body, input, textarea {
            font-family: 'Ubuntu', sans-serif;
        }

        .container {
            padding-bottom: 20px;
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
        .nav-item.active {
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

        #main-text {
            margin-top: 100px;
            padding: 30px;
            background-color: rgba(33, 33, 33, 0.9);
            text-align: justify;
            color: #212121;
        }

        .fc-center h2,
        .fc-day-header,
        .fc-day-number {
            color: #e0e0e0 !important;
        }

        .fc-day.fc-widget-content.fc-today {
            background-color: rgba(163, 0, 0, 0.7) !important;
        }

        .fc-day-top.fc-wed.fc-today {
            background-color: unset !important;
        }

        .fc-day:hover.fc-widget-content {
            background-color: rgba(163, 0, 0, 0.3) !important;
        }

        .fc-row:hover {
            cursor: pointer;
        }

        .fc-widget-content {
            height: 100px !important;
        }
    </style>
<body>

<div class="container">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" id="logo-text" href="${pageContext.request.contextPath}/">Barbershop</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <%
                        List<Menu> menu = (List) request.getAttribute("menuList");
                        for (Menu mItem : menu) {
                    %>
                    <li class="nav-item <%= mItem.getActive() ? "active" : "" %>">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/<%= mItem.getUrl() %>"><%= mItem.getName() %>
                        </a>
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Title -->
    <div id="main-text">
        <div id="calendar"></div>
    </div>
</div>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newEventModal" id="modalButton"
        style="display: none;"></button>

<!-- Modal -->
<div class="modal fade" id="newEventModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"
     style="padding-top: 100px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/reservations" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Make a reservation</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <label for="visit-date" class="col-4 col-form-label">Chosen date:</label>
                        <div class="col-8">
                            <input id="visit-date" type="text" class="form-control" disabled>
                            <input id="visit-date-hidden" name="date" type="hidden" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row"><div class="col-8" style="height: 10px;"></div></div>
                    <div class="form-group row">
                        <label for="service" class="col-4 col-form-label">Choose service:</label>
                        <div class="col-8">
                            <select id="service" name="service" class="form-control">
                                <%
                                    for (Service s: (List<Service>) request.getAttribute("servicesList")) {
                                        %> <option value="<%=s.getId()%>"><%=s.getName()%></option> <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row"><div class="col-8" style="height: 10px;"></div></div>
                    <div class="form-group row">
                        <label for="name" class="col-4 col-form-label">Your name:</label>
                        <div class="col-8">
                            <input id="name" name="name" type="text" class="form-control" required="required">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button name="submit" type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/interaction/main.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/timegrid/main.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/list/main.min.js'></script>
<script>
  $(function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: ['interaction', 'dayGrid', 'timeGrid', 'list'],
      height: 'parent',
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth'
      },
      defaultView: 'dayGridMonth',
      navLinks: false,
      editable: true,
      eventLimit: true,
      events: <%= request.getAttribute("calendarEvents") %>,
      dateClick: function (info) {
        $("#visit-date").val(info.dateStr);
        $("#visit-date-hidden").val(info.dateStr);
        $("#modalButton").click();
      }
    });

    calendar.render();
  });
</script>

</body>
</html>