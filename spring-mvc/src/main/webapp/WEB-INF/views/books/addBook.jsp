<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <jsp:include page="../layout/header.jspf" />
    <h1>Ajouter un nouveau livre</h1>
    <form action="/books/add" method="post" class="row g-3">
        <div class="col-md-6">
            <label for="title" class="form-label">Titre</label>
            <input type="text" class="form-control" name="title">
        </div>

        <div class="col-md-6">
            <label for="author" class="form-label">Auteur</label>
            <input type="text" class="form-control" name="author">
        </div>

        <div class="col-md-6">
            <label for="year" class="form-label">Année</label>
            <input type="text" class="form-control" name="year">
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Enregistrer</button>
        </div>


    </form>
    <jsp:include page="../layout/footer.jspf" />