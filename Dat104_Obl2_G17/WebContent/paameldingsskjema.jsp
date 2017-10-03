<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Påmelding</title>
</head>
<body>
<h2>Påmelding</h2>
<form action="paameldingsskjema" method="post">
  <fieldset>
    <legend>Personlige data</legend>
    <p>Fornavn: <input type="text" name="fornavn"/>  <c:if test="${not empty errorFornavn}">
    ${errorFornavn}
</c:if></p>
    <p>Etternavn: <input type="text" name="etternavn"/>  <c:if test="${not empty errorEtternavn}">
    ${errorEtternavn}
</c:if></p>
    <p>Mobil (8 siffer): <input type="text" name="mobil"/>  <c:if test="${not empty errorMobil}">
    ${errorMobil}
</c:if></p>
    <p>Kjønn: <input type="radio" name="kjonn" value="mann" checked="checked" />mann
        <input type="radio" name="kjonn" value="kvinne" />kvinne</p>
    <p><input type="submit" value="Meld meg på" /></p>
  </fieldset>
</form>
</body>
</html>