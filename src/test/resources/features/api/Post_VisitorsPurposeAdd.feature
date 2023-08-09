
Feature: Bir yönetici olarak API baglantisi üzerinden yeni bir visitor purpose kaydi olusturabilmek istiyorum

  Scenario: Success Response

    Given Api kullanicisi "api/visitorsPurposeAdd" path parametreleri set eder.
    Then VisitorsPurpose icin POST request gonderilir.