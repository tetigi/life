package com.tetigi.resources

import com.monzo.api.MonzoTokenResponse
import com.tetigi.api.LifeService

class LifeResource : LifeService {
    override fun authCallback(authorizationCode: String, stateToken: String): MonzoTokenResponse = TODO()
}
