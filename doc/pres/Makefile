.PHONY: all pres handout clean

all: pres handout

pres:
	pdflatex pres.tex
	#bibtex   pres.aux
	pdflatex pres.tex
	pdflatex pres.tex
	pdflatex pres.tex

handout:
	pdflatex pres_handout.tex
	#bibtex   pres_handout.aux
	pdflatex pres_handout.tex
	pdflatex pres_handout.tex
	pdflatex pres_handout.tex

clean:
	rm -f *.aux
	rm -f *.bbl
	rm -f *.blg
	rm -f *.log
	rm -f *.nav
	rm -f *.out
	rm -f *.snm
	rm -f *.toc
	rm -f *.lot
	rm -f *.lof

