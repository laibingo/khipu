package khipu.network

import org.spongycastle.crypto.digests.KeccakDigest
import scala.concurrent.duration.FiniteDuration

package object rlpx {
  final case class Secrets(
    aes:        Array[Byte],
    mac:        Array[Byte],
    token:      Array[Byte],
    egressMac:  KeccakDigest,
    ingressMac: KeccakDigest
  )

  trait RLPxConfiguration {
    val waitForHandshakeTimeout: FiniteDuration
    val waitForTcpAckTimeout: FiniteDuration
  }

  trait PeerConfiguration {
    val connectRetryDelay: FiniteDuration
    val connectMaxRetries: Int
    val disconnectPoisonPillTimeout: FiniteDuration
    val waitForHelloTimeout: FiniteDuration
    val waitForStatusTimeout: FiniteDuration
    val waitForChainCheckTimeout: FiniteDuration
    val fastSyncHostConfiguration: FastSyncHostConfiguration
    val rlpxConfiguration: RLPxConfiguration
    val maxPeers: Int
    val maxIncomingPeers: Int
    val networkId: Int
    val updateNodesInitialDelay: FiniteDuration
    val updateNodesInterval: FiniteDuration
  }

  trait FastSyncHostConfiguration {
    val maxBlocksHeadersPerMessage: Int
    val maxBlocksBodiesPerMessage: Int
    val maxReceiptsPerMessage: Int
    val maxMptComponentsPerMessage: Int
  }
}
