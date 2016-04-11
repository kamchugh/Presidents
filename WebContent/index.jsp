<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="Presidents.css" />
<link href='https://fonts.googleapis.com/css?family=PT+Serif+Caption'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>
<title>Presidents</title>
</head>
<body>
	<header>
	<table class="navbar">
		<tbody>
			<tr class="headerText">
				<th><a class="headerText" href="#term"> Pick by term </a></th>
				<th><a class="headerText" href="#slideshow"> View Slideshow
				</a></th>
				<th><a class="headerText" href="#party"> Pick by party </a></th>
				<th><a class="headerText" href="#history"> View search
						history </a></th>
			</tr>
		</tbody>
	</table>
	</header>
	<div class="top">
		<div class="toptext">
			<h1>Presidents Web App</h1>
			<h3>utilizing java, jstl, and a servlet</h3>
			<br>
		</div>
	</div>
	<div class="forms">
		<table class="presinfo">
			<tbody>
				<tr>
					<td id="term">

						<h1>Pick a presidential term to display a president</h1>

						<form action="Presidents" method="GET">
							<input type="text" name="term" placeholder="term" /> <input
								type="submit" value="Go!" />
						</form> <br> President # ${presidentForSlideshow.termNumber} is
						${presidentForSlideshow.firstName}
						${presidentForSlideshow.lastName }

					</td>
					<td id="slideshow">
						<h1>View all presidents in a slideshow</h1>
						<form action="Presidents" method="GET">
							<table>
								<tbody>

									<tr>
										<!-- image stuff -->
										<td colspan="3"><img src="${imageURL}" /></td>
									</tr>
									<tr>
										<td>
											<button type="submit" name="changePresident" value="previous">Previous</button>
										</td>
										<td>${presidentForSlideshow.firstName}
											${presidentForSlideshow.lastName }</td>

										<td>
											<button type="submit" name="changePresident" value="next">Next</button>
										</td>
									</tr>

									<tr>


									</tr>


								</tbody>

							</table>
						</form>
					</td>

				</tr>
			</tbody>
		</table>
		<div id="partyCentered">
		<table id="party" class="presinfo">
			<tbody>
				<tr>
					<td>

						<form action="Presidents" method="GET">

							<h1>Display presidents in a specific party</h1>

							Select A Party:<select name="party">
								<option></option>
								<option value="Democrat">Democrat</option>
								<option value="Republican">Republican</option>
								<option value="Whig">Whig</option>
								<option value="Independent">Independent</option>
								<option value="Federalist">Federalist</option>
								<option value="Democratic-Republican">Democratic-Republican</option>
								<input id="goParty" type="submit" value="GO!" />

							</select> <br>
							<c:if test="${!empty(partyToDisplay)}">
			The presidents in the ${partyToDisplay} party:
		</c:if>
							<br>
							<c:forEach var="presByParty" items="${fullPresidentList}">
								<c:if
									test="${! empty(presByParty) && partyToDisplay.equals(presByParty.party)}">
				
    	${presByParty.firstName} ${presByParty.lastName} ${president.party}, president # ${presByParty.termNumber}. <br>
								</c:if>
							</c:forEach>

						</form>
					</td>
					<td>
						<h1>Your search history:</h1>
					
				
					<c:forEach var="president" items="${presidents}">
							<c:if test="${! empty(president)}">
    President # ${president.termNumber} is ${president.firstName} ${president.lastName}. <br>
							</c:if>
						</c:forEach></td>
					
			</tbody>
		</table>
		</div>

	</div>
	<footer>
	<p class="footertext">Back and front end by Kaylee McHugh, Alex
		Peterson, and Chris Rioux</p>
	</footer>
</body>
</html>