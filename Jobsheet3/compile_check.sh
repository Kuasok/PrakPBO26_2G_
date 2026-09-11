#!/usr/bin/env bash
set -e
ROOT="$(cd "$(dirname "$0")" && pwd)"
TMP="$ROOT/.compile-check"
rm -rf "$TMP"
mkdir -p "$TMP"

compile_run() {
  local dir="$1" main="$2" out="$3"; shift 3
  mkdir -p "$TMP/$out"
  javac -d "$TMP/$out" "$ROOT/$dir"/*.java
  java -cp "$TMP/$out" "$main" > /dev/null
  echo "OK: $dir"
}
compile_run "src/percobaan/1-enkapsulasi" "motorencapsulation.MotorDemo" exp1
compile_run "src/percobaan/2-access-modifier" "motorencapsulation.MotorDemo" exp2
compile_run "src/percobaan/3-getter-setter" "koperasigettersetter.KoperasiDemo" exp3
compile_run "src/percobaan/4-konstruktor" "koperasigettersetter.KoperasiDemo" exp4
compile_run "src/tugas/1-3-age" "EncapTest" t13
compile_run "src/tugas/4-kontainer" "TestLogistik" t4
compile_run "src/tugas/5-kontainer-50persen" "TestLogistik" t5
printf 'REQ-9988\nPT. Maju Bersama\n5000\n4000\n2000\n' | bash -c 'javac -d "$0" "$1"/*.java && printf "REQ-9988\\nPT. Maju Bersama\\n5000\\n4000\\n2000\\n" | java -cp "$0" TestLogistik > /dev/null' "$TMP/t6" "$ROOT/src/tugas/6-logistik-scanner"
compile_run "src/tugas/7-tiket" "TestBioskop" t7
rm -rf "$TMP"
echo "ALL CHECKS PASSED"
