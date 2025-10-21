<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <jsp:include page="../layout/header.jspf" />
    <h1>Détails du livre ${book.title}</h1>
    <ul>
        <li><strong>Titre :</strong> ${book.title}</li>
        <li><strong>Auteur :</strong> ${book.author}</li>
        <li><strong>Année :</strong> ${book.year}</li>
    </ul>
    <jsp:include page="../layout/footer.jspf" />