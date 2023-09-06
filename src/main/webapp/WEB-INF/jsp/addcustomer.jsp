<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Add Customer</title>
    <!-- Add Bootstrap CSS link here -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    />
  </head>
  <body>
    <div class="container">
      <h1>${action == 'edit' ? 'Edit' : 'Add'} Customer</h1>
      <form
      <c:choose>
        <c:when test="${action == 'edit'}">
            action="/edit-customer?uuid=${customer.uuid}"
        </c:when>
        <c:otherwise>
            action="/add-customer"
        </c:otherwise>
    </c:choose> method="post"
      >
        <div class="row">
          <div class="col-md-6 form-group">
            <label for="firstName">First Name:</label>
            <input
              type="text"
              class="form-control"
              id="firstName"
              name="firstName"
              value="${customer.firstName}"
              required
            />
          </div>

          <div class="col-md-6 form-group">
            <label for="lastName">Last Name:</label>
            <input
              type="text"
              class="form-control"
              id="lastName"
              name="lastName"
              value="${customer.lastName}"
              required
            />
          </div>
        </div>

        <div class="row">
          <div class="col-md-6 form-group">
            <label for="address">Address:</label>
            <input
              type="text"
              class="form-control"
              id="address"
              name="address"
              value="${customer.address}"
              required
            />
          </div>

          <div class="col-md-6 form-group">
            <label for="city">City:</label>
            <input
              type="text"
              class="form-control"
              id="city"
              name="city"
              value="${customer.city}"
              required
            />
          </div>
        </div>

        <div class="row">
          <div class="col-md-6 form-group">
            <label for="state">State:</label>
            <input
              type="text"
              class="form-control"
              id="state"
              name="state"
              value="${customer.state}"
              required
            />
          </div>

          <div class="col-md-6 form-group">
            <label for="email">Email:</label>
            <input
              type="email"
              class="form-control"
              id="email"
              name="email"
              value="${customer.email}"
              required
            />
          </div>
        </div>

        <div class="row">
          <div class="col-md-6 form-group">
            <label for="phone">Phone:</label>
            <input
              type="text"
              class="form-control"
              id="phone"
              name="phone"
              value="${customer.phone}"
              required
            />
          </div>
        </div>

        <div class="mt-3 row">
          <div class="col-md-12">
            <input
              type="submit"
              class="btn btn-primary"
              value="${action == 'edit' ? 'Edit Customer' : 'Add Customer'}"
            />
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
