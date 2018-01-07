# A Makefile for HW5 by Joven Pableo
#-------------------------------------------------------------------

JAVASRC    = HashClient.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = HashClient
CLASSES    = HashClient.class
JARFILE    = Bard.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
