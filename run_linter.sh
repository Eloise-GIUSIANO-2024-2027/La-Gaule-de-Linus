#!/bin/bash

# ./run_linter.sh

# Variables checkstyle
CHECKSTYLE_VERSION="10.12.5"
CHECKSTYLE_JAR="checkstyle-${CHECKSTYLE_VERSION}-all.jar"
CHECKSTYLE_URL="https://github.com/checkstyle/checkstyle/releases/download/checkstyle-${CHECKSTYLE_VERSION}/${CHECKSTYLE_JAR}"

# Couleurs pour que ce soit zoli
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NOCOLOR='\033[0m'

echo "/-/ Linter /-/"

# Télécharger checkstyle si besoin
if [ ! -f "$CHECKSTYLE_JAR" ]; then
    echo -e "${YELLOW}    - Téléchargement de checkstyle version ${CHECKSTYLE_VERSION}${NOCOLOR}"
    curl -L -o "$CHECKSTYLE_JAR" "$CHECKSTYLE_URL"

    if [ $? -ne 0 ]; then
        echo -e "${RED}   - Erreur lors du téléchargement de Checkstyle${NOCOLOR}"
        exit 1
    fi
    echo -e "${GREEN}   - Checkstyle téléchargé avec succès${NOCOLOR}"
    echo ""
fi

# Vérifier tous les fichiers avec checkstyle
echo -e "${YELLOW}    - Analyse du code${NOCOLOR}"

java -jar "$CHECKSTYLE_JAR" -c checkstyle.xml src/