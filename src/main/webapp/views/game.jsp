<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Devine un nombre</title>
	</head>
	<body onload="document.guessForm.guess.focus()">
		<hr>
		<h2>${player_count} joueurs connectés</h2>
		<hr>

		<h3>Hello ${player.username}, Devine mon nombre</h3>
                
                <c:if test="${player.lastGuess != -1}">
                    Essai n°<b>${player.guessCount}</b><br/>
                    ${player.lastGuess}: <b>${player.lastGuessComment}</b>
                </c:if>
		
		<h2>je pense à un nombre compris entre 0 et 100</h2>
                
		<form name="guessForm" method="POST" accept-charset="UTF-8" >
			<label>Ta proposition : <input type="number" min="0" max="100" required name="guess"></label> 
			<input type="SUBMIT" name="action" value="Deviner"><br/>
		</form>
                
		<form method="POST">
			<input type="SUBMIT" name="action" value="Deconnexion">
		</form>

		<hr>

                <c:if test="${bestplayer.guessCount != 0}">
                    <h2>Score à battre : ${bestplayer.guessCount} essais par ${bestplayer.username}</h2>
                </c:if>
	</body>
</html>