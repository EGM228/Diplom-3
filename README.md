task 3 diplom

Запуск хром
```bash
mvn test
```

Запуск мозилы
```bash
mvn -Dbrowser=firefox test
```

Если предыдущий падает с ошибкой 500 то берм этот
```bash
mvn -Dbrowser=firefox -Dwebdriver.firefox.bin="/Program Files/Mozilla Firefox/firefox.exe" test
```