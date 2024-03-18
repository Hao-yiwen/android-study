package com.example.amphibians.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AmphibianTypeSerializer : KSerializer<AmphibianType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("AmphibianType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): AmphibianType {
        val type = decoder.decodeString()
        return AmphibianType.fromType(type) ?: throw IllegalArgumentException("Unknown type: $type")
    }

    override fun serialize(encoder: Encoder, value: AmphibianType) {
        encoder.encodeString(value.toString())
    }

}