.PHONY: all light clean

all: 
	pdflatex doc.tex
	bibtex   doc.aux
	pdflatex doc.tex
	pdflatex doc.tex
	pdflatex doc.tex

light:
	pdflatex doc_light.tex
	bibtex   doc_light.aux
	pdflatex doc_light.tex
	pdflatex doc_light.tex
	pdflatex doc_light.tex

clean:
	rm -f *.aux
	rm -f *.bbl
	rm -f *.blg
	rm -f *.log
	rm -f *.out
	rm -f *.toc
	rm -f *.lot
	rm -f *.lof

