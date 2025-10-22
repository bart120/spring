<jsp:include page="layout/header.jspf" />

<form method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" />
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" />
    </div>
    <div>
        <button type="submit">Login</button>
    </div>

</form>


<jsp:include page="layout/footer.jspf" />