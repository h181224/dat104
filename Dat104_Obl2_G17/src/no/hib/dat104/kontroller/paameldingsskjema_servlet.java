package no.hib.dat104.kontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

@WebServlet("/paameldingsskjema")
public class paameldingsskjema_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int fornavnLengde;
	int etternavnLengde;
	int mobilLengde;
	int mobilNr;
	boolean gyldig = true;
	String errorFornavn;
	String errorEtternavn;
	String errorMobil;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fornavn = request.getParameter("fornavn");
		String etternavn = request.getParameter("etternavn");
		String mobil = request.getParameter("mobil");
		String kjonn = request.getParameter("kjonn");

		StringEscapeUtils.escapeHtml(fornavn);
		StringEscapeUtils.escapeHtml(etternavn);
		StringEscapeUtils.escapeHtml(mobil);

		fornavnLengde = fornavn.length();
		etternavnLengde = etternavn.length();
		mobilLengde = mobil.length();
	

		if (fornavn.equals(null) || etternavn.equals(null) || mobil.equals(null)) {
			errorFornavn = "Kan ikke være tomt";
			errorEtternavn = "Kan ikke være tomt";
			errorMobil = "Kan ikke være tomt";
			gyldig = false;
			System.out.println(gyldig);
		} else if (fornavnLengde > 20 || fornavnLengde < 2) {
			errorFornavn = "Må være mellom 2 og 20 bokstaver i lengde";
			gyldig = false;
			System.out.println(gyldig);
		} else if (mobilLengde != 8) {
			errorMobil = "Må være 8 siffre";
			gyldig = false;
			System.out.println(gyldig);
		} else if (etternavnLengde > 20 || etternavnLengde < 2) {
			errorEtternavn = "Må være mellom 2 og 20 bokstaver i lengde";
			gyldig = false;
			System.out.println(gyldig);
		} 
		

		if (gyldig==true) {
			HttpSession sesjon = request.getSession(false);
			if (sesjon != null) {
				sesjon.invalidate();
			}
			sesjon = request.getSession(true);
			sesjon.setMaxInactiveInterval(30);
			sesjon.setAttribute("fornavn", fornavn);
			sesjon.setAttribute("etternavn", etternavn);
			sesjon.setAttribute("mobil", mobil);
			sesjon.setAttribute("konn", kjonn);
			response.sendRedirect("paameldingsbekreftelse");
		} else if (gyldig==false){
			request.setAttribute("errorFornavn", errorFornavn);
			request.setAttribute("errorEtternavn", errorEtternavn);
			request.setAttribute("errorMobil", errorMobil);
			request.getRequestDispatcher("/paameldingsskjema.jsp").forward(request, response);
		}

	}



}
