<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<html>
  <head>
    <title>Customer List</title>
    <!-- Include Bootstrap CSS -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    />
  </head>
  <body>
    <div class="container">
      <h1>Customer List</h1>
      <a href="/add-customer" class="btn btn-success mb-2">Add Customer</a>

      <table class="table table-striped table-bordered">
        <thead class="thead-dark">
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${tableData}" var="customer">
            <tr>
              <td>${customer.firstName}</td>
              <td>${customer.lastName}</td>
              <td>${customer.address}</td>
              <td>${customer.city}</td>
              <td>${customer.state}</td>
              <td>${customer.email}</td>
              <td>${customer.phone}</td>
              <td>
                <a
                  href="/edit-customer?uuid=${customer.uuid}"
                  class="btn btn-primary"
                  >Edit</a
                >
                <a
                  href="/delete-customer?uuid=${customer.uuid}"
                  class="btn btn-danger"
                  >Delete</a
                >
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

    <!-- Include Bootstrap JavaScript (optional) -->
    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script> -->
  </body>
</html>
