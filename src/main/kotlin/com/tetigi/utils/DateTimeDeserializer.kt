package com.tetigi.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.joda.time.DateTime


class DateTimeDeserializer : JsonDeserializer<DateTime>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): DateTime {
        val date = p?.text
        if (date != null) {
            return DateTime.parse(date)
        }
        error("Could not deserialize datetime from $p")
    }
}
