package esgi.com.deezerApp.data.dto.mapper

import esgi.com.deezerApp.data.dto.EError
import esgi.com.deezerApp.data.model.DeezerError

class EErrorMapper {
    fun map(eError: EError): DeezerError{
        return DeezerError(eError.type, eError.message, eError.code)
    }
}