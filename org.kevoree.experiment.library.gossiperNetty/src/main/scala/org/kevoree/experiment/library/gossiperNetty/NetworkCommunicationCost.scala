package org.kevoree.experiment.library.gossiperNetty


/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 21/07/11
 * Time: 17:47
 */

object NetworkCommunicationCost extends actors.DaemonActor {
  private var dataSizeReceived = 0l;
  start()

  case class STOP ()

  case class GETVALUEDATASIZERECEIVED ()

  case class UPDATEDATASIZERECEIVED (size: Long)

  case class REINITIALIZEDDATASIZERECEIVED ()

  def stop () {
    this ! STOP()
  }

  def updateDataSizeReceived (size: Long) {
    this !? UPDATEDATASIZERECEIVED(size)
  }

  def reinitializedDataSizeReceived () {
    this !? REINITIALIZEDDATASIZERECEIVED()
  }

  def getValue: Long = {
    (this !? GETVALUEDATASIZERECEIVED()).asInstanceOf[Long]
  }

  /* PRIVATE PROCESS PART */
  def act () {
    loop {
      react {
        case STOP => {
          this.exit()
        }
        case UPDATEDATASIZERECEIVED(size) => {
          dataSizeReceived += size
        }
        case REINITIALIZEDDATASIZERECEIVED => {
          dataSizeReceived = 0
        }
        case GETVALUEDATASIZERECEIVED => reply(dataSizeReceived)
      }
    }
  }

}