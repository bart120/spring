<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <jsp:include page="../layout/header.jspf" />

    <h1>Liste des livres</h1>
    <p><a class="btn btn-primary" href="/books/add">Nouveau livre</a></p>
    <table class="table table-striped align-middle">
        <thead>
            <tr>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Année</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${listBooks}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.year}</td>
                    <td><a class="btn btn-secondary" href="/books/${book.id}">Voir</a></td>
                    <td><a class="btn btn-danger" href="/books/delete/${book.id}">Supprimer</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <jsp:include page="../layout/footer.jspf" />