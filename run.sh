#!/bin/bash

set -a
source .env
set +a

mvn compile
mvn exec:java -Dexec.mainClass=com.hesoyam.pharmacy.HesoyamPharmacyApplication
