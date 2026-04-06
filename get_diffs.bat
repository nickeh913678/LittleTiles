@echo off
cd /d f:\LT3

echo === COMMIT 1: e23a3f045 ===
git show e23a3f045 > diff1.txt 2>&1

echo === COMMIT 2: b7c0f667e ===
git show b7c0f667e > diff2.txt 2>&1

echo Done. Check diff1.txt and diff2.txt
