package ru.reksoft.entities

import org.springframework.data.annotation.Id;

data class Task(@Id var id: String?, var content: String)