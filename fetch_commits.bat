@echo off
cd /d f:\LT3
echo === Commit e23a3f045 ===
git show e23a3f045 > commit_e23a3f045.txt 2>&1
echo === Commit b7c0f667e ===
git show b7c0f667e > commit_b7c0f667e.txt 2>&1
echo === Commit d374f3a34 ===
git show d374f3a34 > commit_d374f3a34.txt 2>&1
echo Commits saved to .txt files
dir commit_*.txt
